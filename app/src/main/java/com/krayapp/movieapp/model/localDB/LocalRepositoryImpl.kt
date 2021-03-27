package com.krayapp.movieapp.model.localDB

import com.krayapp.movieapp.model.MovieInfo
import com.krayapp.movieapp.room.MovieDAO
import com.krayapp.movieapp.utils.convertHistoryEntityToMovie
import com.krayapp.movieapp.utils.convertMovieCacheToEntity

class LocalRepositoryImpl(private val localDataSource:MovieDAO):LocalRepository {
    override fun getAllNotes(): List<MovieInfo> {
        return convertHistoryEntityToMovie(localDataSource.all())
    }

    override fun saveEntity(movieInfo: MovieInfo) {
        localDataSource.insert(convertMovieCacheToEntity(movieInfo))
    }

}