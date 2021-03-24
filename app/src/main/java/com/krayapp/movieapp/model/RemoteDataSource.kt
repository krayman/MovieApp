package com.krayapp.movieapp.model

import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    private  val movieApi = Retrofit.Builder()
        .baseUrl("https://api.tmdb.org/")
        .addConverterFactory(GsonConverterFactory.create(
            GsonBuilder().setLenient().create()))
        .build().create(MovieAPI::class.java)

    fun getMovieList(genre:String, api_key: String, callback: Callback<MovieDTO>){
        movieApi.getMovie(genre, api_key, "ru-RU").enqueue(callback)
    }
}