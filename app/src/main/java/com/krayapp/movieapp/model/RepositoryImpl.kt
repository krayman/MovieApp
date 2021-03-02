package com.krayapp.movieapp.model

class RepositoryImpl:Repository {

    override fun getMovieFromServer(): MovieInfo {
        return MovieInfo()
    }

    override fun getMovieFromLocalStore(): MovieInfo {
        return MovieInfo()
    }
}