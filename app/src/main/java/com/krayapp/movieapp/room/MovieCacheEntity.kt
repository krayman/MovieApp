package com.krayapp.movieapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieCacheEntity(
    @PrimaryKey
    val id: Int?,
    val title: String?,
    val description: String?,
    val rate: Float?,
    val imagePath: String?

) {
}