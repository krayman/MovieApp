package com.krayapp.movieapp.model

import android.os.Build
import androidx.annotation.RequiresApi

class RepositoryImpl : Repository {
   /* lateinit var dtoFromServer : MovieDTO
    @RequiresApi(Build.VERSION_CODES.N)
    private val onLoadListener: MovieLoader.MovieLoaderListener =
        object : MovieLoader.MovieLoaderListener {
            override fun onLoaded(movieDTO: MovieDTO) {
                dtoFromServer = movieDTO
            }

            override fun onFailed(throwable: Throwable) {
            }

        }
    @RequiresApi(Build.VERSION_CODES.N)
    val listener = MovieLoader(onLoadListener)
    private fun dtoToMovieInfo(movieDTO: MovieDTO): MutableList<MovieInfo> {
        val movieInfoMutableList: MutableList<MovieInfo> = mutableListOf()
        for (movieDTO in movieDTO.results) {
                movieInfoMutableList.add(
                    MovieInfo(
                        movieDTO.title,
                        movieDTO.overview,
                        movieDTO.vote_average
                    )
                )
            }
        return movieInfoMutableList
    }*/

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getMovieFromServer(): MutableList<MovieInfo> {
        return mutableListOf()
    }

    override fun getAdventureMovieFromLocal(): List<MovieInfo> {
        return getAdventureMovie()
    }

    override fun getScaredMovieFromLocal(): List<MovieInfo> {
        return getScaredMovie()
    }

    override fun getMovieFromLocalStore(): MovieInfo {
        return MovieInfo()
    }
}