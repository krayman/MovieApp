package com.krayapp.movieapp.model.retrofit

import com.krayapp.movieapp.model.MovieDTO

interface MovieRepository {
    fun getMovieListFromServer(
        genre: String,
        api_key: String,
        callback: retrofit2.Callback<MovieDTO>
    )
}