package com.krayapp.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.krayapp.movieapp.app.App.Companion.getMovieDAO
import com.krayapp.movieapp.app.AppState
import com.krayapp.movieapp.model.MovieInfo
import com.krayapp.movieapp.model.localDB.LocalRepository
import com.krayapp.movieapp.model.localDB.LocalRepositoryImpl

class CacheViewModel (
    val cacheLiveData : MutableLiveData<AppState> = MutableLiveData(),
    private val cacheRepository:LocalRepository=LocalRepositoryImpl(getMovieDAO())
):ViewModel(){
   fun getAllCache(){
       cacheLiveData.value = AppState.Loading
       cacheLiveData.value = AppState.Success(cacheRepository.getAllNotes() as MutableList<MovieInfo>)
   }
}