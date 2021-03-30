package com.krayapp.movieapp.model.localDB

import com.krayapp.movieapp.model.MovieInfo

interface LocalRepository {
    fun getAllNotes(): List<MovieInfo>
    fun saveEntity(movieInfo: MovieInfo)
}