package com.krayapp.movieapp.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = arrayOf(MovieCacheEntity::class), version = 1, exportSchema = false)

abstract class RoomMovieDB : RoomDatabase() {

    abstract fun movieDao(): MovieDAO
}