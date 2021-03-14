package com.krayapp.movieapp.model

import android.os.Build
import androidx.annotation.RequiresApi

class RepositoryImpl : Repository {
    var movieList: MutableList<MovieInfo> = mutableListOf()
    @RequiresApi(Build.VERSION_CODES.N)
    private val onLoadListener: MovieLoader.MovieLoaderListener =
        object : MovieLoader.MovieLoaderListener {
            override fun onLoaded(movieDTO: MovieDTO) {
                 movieList = dtoToMovieInfo(movieDTO)
            }

            override fun onFailed(throwable: Throwable) {
            }

        }

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
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getMovieFromServer(): MutableList<MovieInfo> {
        val listener = MovieLoader(onLoadListener)
        listener.loadMovieData()
        return movieList
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