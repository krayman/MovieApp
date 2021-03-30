package com.krayapp.movieapp.app

import com.krayapp.movieapp.model.MovieInfo

sealed class AppState{
    data class Success(val movieData: MutableList<MovieInfo>): AppState()
    data class Error(val error : Throwable): AppState()
    object Loading : AppState()
}