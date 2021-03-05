package com.krayapp.movieapp.model

class RepositoryImpl:Repository {

    override fun getMovieFromServer(): MovieInfo {
        return MovieInfo()
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