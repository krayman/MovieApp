package com.krayapp.movieapp.model

data class MovieDTO(
    val page: Float,
    val results: List<FactDTO>

)

data class FactDTO(
    val title: String,
    val overview: String,
    val vote_average: Float,
    val poster_path: String
)