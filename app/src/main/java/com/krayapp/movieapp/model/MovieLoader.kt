package com.krayapp.movieapp.model

import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

@RequiresApi(Build.VERSION_CODES.N)
class MovieLoader() {
//    private final val MOVIE_API_KEY = "58cb0298f8a3d11c2c5b6afa5a8c7292"
    private final val MOVIE_API_KEY = "f511080097mshec683dc2ca4e579p171fbfjsne44686654503"

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadMovieData() {
        try {
            val uri = URL("https://movie-database-imdb-alternative.p.rapidapi.com/?s=Avengers%20Endgame&page=1&r=json&rapidapi-key=${MOVIE_API_KEY}")
//            val uri = URL("https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term=bojack&country=uk&rapidapi-key=f511080097mshec683dc2ca4e579p171fbfjsne44686654503")
            val handler = Handler(Looper.getMainLooper())
            Thread {
                var urlConnection: HttpsURLConnection? = null
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val movieDTO: MovieDTO =
                        Gson().fromJson<MovieDTO>(getLines(bufferedReader), MovieDTO::class.java)
                    print(movieDTO)
                                      /* handler.post{(listener.onLoaded(movieDTO))}*/
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    urlConnection?.disconnect()
                }
            }.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    interface MovieLoaderListener{
        fun onLoaded(movieDTO: MovieDTO)
        fun onFailed(throwable: Throwable)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}


