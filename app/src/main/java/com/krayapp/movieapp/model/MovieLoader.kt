package com.krayapp.movieapp.model

import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Thread.sleep
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

@RequiresApi(Build.VERSION_CODES.N)
class MovieLoader(private val movieLoaderListener: MovieLoaderListener) {
    private val MOVIE_API_KEY = "58cb0298f8a3d11c2c5b6afa5a8c7292" // TMDb

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadMovieData(genre: String) {
        try {
            val uri = URL("https://api.tmdb.org/3/movie/${genre}?api_key=${MOVIE_API_KEY}")
            val handler = Handler(Looper.getMainLooper())
            Thread(Runnable {
                var urlConnection: HttpsURLConnection? = null
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val movieDTO: MovieDTO =
                        Gson().fromJson<MovieDTO>(getLines(bufferedReader), MovieDTO::class.java)
                    handler.post { movieLoaderListener.onLoaded(movieDTO, genre)}
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    urlConnection?.disconnect()
                }
            }).start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    interface MovieLoaderListener {
        fun onLoaded(movieDTO: MovieDTO, genre: String)
        fun onFailed(throwable: Throwable)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}


