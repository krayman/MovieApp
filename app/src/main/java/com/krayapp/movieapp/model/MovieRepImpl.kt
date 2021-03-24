package com.krayapp.movieapp.model

import retrofit2.Callback

class MovieRepImpl(private val remoteDataSource: RemoteDataSource):MovieRepository {
    override fun getMovieListFromServer(
        genre: String,
        api_key: String,
        callback: Callback<MovieDTO>
    ) {
        remoteDataSource.getMovieList(genre, api_key,callback)
    }
}