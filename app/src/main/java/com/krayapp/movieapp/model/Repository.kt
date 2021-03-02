package com.krayapp.movieapp.model

interface Repository {
    fun getMovieFromServer(): MovieInfo
    fun getMovieFromLocalStore(): MovieInfo
}