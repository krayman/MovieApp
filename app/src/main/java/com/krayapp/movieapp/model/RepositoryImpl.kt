package com.krayapp.movieapp.model

import android.os.Build
import androidx.annotation.RequiresApi

class RepositoryImpl : Repository {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun getMovieFromServer(): MutableList<MovieInfo> {
        return mutableListOf()
    }

    override fun getMovieFromLocalStore(): MovieInfo {
        return MovieInfo()
    }
}