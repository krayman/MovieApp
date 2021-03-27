package com.krayapp.movieapp.app

import android.app.Application
import androidx.room.Room
import com.krayapp.movieapp.room.MovieDAO
import com.krayapp.movieapp.room.RoomMovieDB

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
    companion object{
        private var appInstance: App? = null
        private var db:RoomMovieDB ?= null
        private const val DB_NAME = "MovieCacheDB.db"

        private val movieDao by lazy {
            Room
                .databaseBuilder(appInstance!!.applicationContext,
                RoomMovieDB::class.java, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .movieDao()
        }

        fun getMovieDAO():MovieDAO{
           return movieDao
        }
    }
}