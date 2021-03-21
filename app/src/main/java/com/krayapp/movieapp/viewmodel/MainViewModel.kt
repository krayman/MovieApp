package com.krayapp.movieapp.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.krayapp.movieapp.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val CORRUPTED_DATA = "Неполные данные"
private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"

class MainViewModel(
    val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: MovieRepository = MovieRepImpl(RemoteDataSource())
) : ViewModel() {
    lateinit var genre: String

    fun getMovieDataFromServer(genre: String, api_key: String) {
        this.genre = genre
        liveData.value = AppState.Loading
        repositoryImpl.getMovieListFromServer(genre, api_key, callback)
    }

    private val callback = object : Callback<MovieDTO> {
        override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
            liveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
            val serverResponse: MovieDTO? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun checkResponse(serverResponse: MovieDTO): AppState {
        if (serverResponse.results.get(0).title == null ||
            serverResponse.results.get(0).overview == null
        ) {
            return AppState.Error(Throwable(CORRUPTED_DATA))
        } else {
            return AppState.Success(dtoToMovieInfo(serverResponse), genre)
        }
    }

    private fun dtoToMovieInfo(movieDTO: MovieDTO): MutableList<MovieInfo> {
        val movieInfoMutableList: MutableList<MovieInfo> = mutableListOf()
        for (movieDTO in movieDTO.results) {
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
}