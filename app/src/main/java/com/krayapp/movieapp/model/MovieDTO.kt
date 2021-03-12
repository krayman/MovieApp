package com.krayapp.movieapp.model

data class MovieDTO(
val fact : FactDTO?
)
data class FactDTO(
    val Title: String?,
    val Type : String?
    /*val overview: String?,
    val vote_average: Float?*/
)