package com.krayapp.movieapp.model.retrofit

import com.krayapp.movieapp.model.MovieDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET("3/movie/{genre}")
    fun getMovie(
        @Path("genre") genre: String,
        @Query ("api_key")api_key: String,
        @Query("language")language:String
    ): retrofit2.Call<MovieDTO>
}