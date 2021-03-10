package com.krayapp.movieapp.model

interface Repository {
    fun getMovieFromServer(): List<MovieInfo>
    fun getAdventureMovieFromLocal(): List<MovieInfo>
    fun getScaredMovieFromLocal(): List<MovieInfo>
    fun getMovieFromLocalStore(): MovieInfo
}