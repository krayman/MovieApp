package com.krayapp.movieapp.model

import android.app.IntentService
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import com.krayapp.movieapp.ui.main.mainScreen.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection


private const val MOVIE_API_KEY = "58cb0298f8a3d11c2c5b6afa5a8c7292" // TMDb

class MovieService(name: String = "MovieService") : IntentService(name) {
    private val broadcastIntent = Intent(MOVIE_INTENT_FILTER)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onHandleIntent(intent: Intent?) {
        if (intent == null) {

        } else {
            val genre = intent.getStringExtra(GENRE_EXTRA)
            if (genre != null) {
                loadMovieData(genre)
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadMovieData(genre: String) {
        try {
            val uri = URL("https://api.tmdb.org/3/movie/${genre}?api_key=${MOVIE_API_KEY}")
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
                    onResponse(movieDTO,genre)
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

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun onResponse(movieDTO: MovieDTO, genre: String) {
        if (movieDTO == null) {

        } else {
            onSuccessResponse(dtoToMovieInfo(movieDTO),genre)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun dtoToMovieInfo(movieDTO: MovieDTO): MutableList<MovieInfo> {
        val movieInfoMutableList: MutableList<MovieInfo> = mutableListOf()
        for (movieDTO in movieDTO.results) {
            movieInfoMutableList.add(
                MovieInfo(
                    movieDTO.title,
                    movieDTO.overview,
                    movieDTO.vote_average,
                    movieDTO.poster_path
                )
            )
        }
        return movieInfoMutableList
    }

    private fun onSuccessResponse(movieList: MutableList<MovieInfo>,genre: String) {
        putLoadResult(DETAILS_RESPONSE_SUCCESS_EXTRA, genre)
        broadcastIntent.putParcelableArrayListExtra(MOVIE_LIST_EXTRA, (ArrayList<MovieInfo>(movieList)))
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    private fun putLoadResult(result: String, genre: String) {
        broadcastIntent.putExtra(DETAILS_LOAD_RESULT_EXTRA, result)
        if (result == DETAILS_RESPONSE_SUCCESS_EXTRA) {
            broadcastIntent.putExtra(GENRE_TYPE, genre)
        }
    }
}

