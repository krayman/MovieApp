package com.krayapp.movieapp.room

import androidx.room.*

@Dao
interface MovieDAO{

    @Query("SELECT * FROM MovieCacheEntity")
    fun all(): List<MovieCacheEntity>

    @Query("SELECT * FROM MovieCacheEntity WHERE rate LIKE :rate")
    fun getDataByWord(rate: Float): List<MovieCacheEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: MovieCacheEntity)

    @Update
    fun update(entity: MovieCacheEntity)

    @Delete
    fun delete(entity: MovieCacheEntity)

}