package com.krayapp.movieapp.viewmodel

import com.krayapp.movieapp.model.MovieInfo

sealed class AppState{
    data class Success(val movieData : MovieInfo): AppState()
    data class Error(val error : Throwable): AppState()
    object Loading : AppState()
}