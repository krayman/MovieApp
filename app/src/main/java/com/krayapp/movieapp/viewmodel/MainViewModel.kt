package com.krayapp.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.krayapp.movieapp.model.Repository
import com.krayapp.movieapp.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
) : ViewModel() {
    fun getLiveData() = liveDataToObserve

    fun getAdventureMovieFromLocal() {
        getDataFromLocalSource(0)
    }
    fun getScaredMovieFromLocal() {
        getDataFromLocalSource(1)
    }

    private fun getDataFromLocalSource(genre: Int) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(
                AppState.Success(if (genre == 0) repositoryImpl.getAdventureMovieFromLocal()
                else repositoryImpl.getScaredMovieFromLocal()))
        }.start()
    }
}