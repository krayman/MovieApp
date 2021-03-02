package com.krayapp.movieapp.model

interface MovieSource {
    fun init()
    fun getMovieInfo(position:Int)
    var size:Int
}