package com.krayapp.movieapp.model

interface MovieRepository {
    fun getMovieListFromServer(
        genre: String,
        api_key: String,
        callback: retrofit2.Callback<MovieDTO>
    )
}