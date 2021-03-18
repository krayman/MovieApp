package com.krayapp.movieapp.model

interface Repository {
    fun getMovieFromServer(): MutableList<MovieInfo>
    fun getMovieFromLocalStore(): MovieInfo
}