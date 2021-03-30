package com.krayapp.movieapp.utils

import com.krayapp.movieapp.model.MovieDTO
import com.krayapp.movieapp.model.MovieInfo
import com.krayapp.movieapp.room.MovieCacheEntity
import com.krayapp.movieapp.ui.main.mainScreen.ListerFragment

fun dtoToMovieInfo(movieDTO: MovieDTO): MutableList<MovieInfo> {
    val movieInfoMutableList: MutableList<MovieInfo> = mutableListOf()
    for (movieDTO in movieDTO.results) {
        if (movieDTO.adult == ListerFragment.adult || movieDTO.adult == false)
            movieInfoMutableList.add(
                MovieInfo(
                    movieDTO.title,
                    movieDTO.overview,
                    movieDTO.vote_average,
                    movieDTO.poster_path
                )
            )
    }
    return movieInfoMutableList
}


fun convertHistoryEntityToMovie(entityList: List<MovieCacheEntity>):List<MovieInfo>{
    return entityList.map{
        MovieInfo(it.title, it.description, it.rate, it.imagePath)
    }
}

var dbIterator = 0
fun convertMovieCacheToEntity(movieInfo: MovieInfo):MovieCacheEntity{
    dbIterator++
    return MovieCacheEntity(dbIterator, movieInfo.title, movieInfo.description,movieInfo.rate, movieInfo.imagePath)
}