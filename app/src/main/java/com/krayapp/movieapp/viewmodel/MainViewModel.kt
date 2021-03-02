package com.krayapp.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.krayapp.movieapp.model.MovieInfo
import com.krayapp.movieapp.model.Repository
import com.krayapp.movieapp.model.RepositoryImpl

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
) : ViewModel() {
    fun getLiveData() = liveDataToObserve
    fun getMovieFromLocalStore() {
        getDataFromLocalSource()
    }

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            liveDataToObserve.postValue(
                AppState.Success(
                    MovieInfo(
                        "Второй лучший фильм",
                        "Возможно",
                        3
                    )
                )
            )
        }.start()
    }
}