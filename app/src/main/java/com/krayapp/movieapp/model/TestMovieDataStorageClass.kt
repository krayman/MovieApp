package com.krayapp.movieapp.model

class TestMovieDataStorageClass(override var size: Int) : MovieSource {
    private val movieData: ArrayList<MovieInfo> = ArrayList()
    override fun init() {
        movieData.add(MovieInfo("Третий фильм", "ТОже хороший", 6))
        movieData.add(MovieInfo("Четвертый фильм", "Неплох", 4))
        movieData.add(MovieInfo("Пятый фильм", "Совсем не очя", 2))
    }

    override fun getMovieInfo(position:Int) {
        movieData[position]
    }
}



