package com.krayapp.movieapp.ui.main.mainScreen

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.snackbar.Snackbar
import com.krayapp.movieapp.R
import com.krayapp.movieapp.databinding.MainFragmentBinding
import com.krayapp.movieapp.model.MovieDTO
import com.krayapp.movieapp.model.MovieInfo
import com.krayapp.movieapp.model.MovieService
import com.krayapp.movieapp.ui.main.aboutMovie.AboutMovieFragment
import com.krayapp.movieapp.viewmodel.MainViewModel

const val MOVIE_INTENT_FILTER = "DETAILS INTENT FILTER"
const val DETAILS_LOAD_RESULT_EXTRA = "LOAD RESULT"
const val GENRE_TYPE = "GENRE TYPE"
const val MOVIE_LIST_EXTRA = "MOVIE LIST EXTRA"
const val GENRE_EXTRA = "GENRE_EXTRA"
const val DETAILS_RESPONSE_SUCCESS_EXTRA = "RESPONSE SUCCESS"

class ListerFragment : Fragment() {
    private val connectStatusReceiver: BroadcastReceiver = object : BroadcastReceiver(){
        @SuppressLint("ShowToast")
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "Internet changed", Toast.LENGTH_LONG)
        }

    }
    private val loadResultsReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.getStringExtra(DETAILS_LOAD_RESULT_EXTRA)) {
                DETAILS_RESPONSE_SUCCESS_EXTRA -> {
                    val genre = intent.getStringExtra(GENRE_TYPE).toString()
                    val mutableList : MutableList<MovieInfo> =
                        intent.getParcelableArrayListExtra<MovieInfo>(MOVIE_LIST_EXTRA)?.toMutableList()!!
                    setMovieList(mutableList,genre)
                }
            }
        }
    }


    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            LocalBroadcastManager.getInstance(it)
                .registerReceiver(loadResultsReceiver, IntentFilter(MOVIE_INTENT_FILTER))
        }
        context?.registerReceiver(connectStatusReceiver, IntentFilter(CONNECTIVITY_ACTION))
    }

    private val popularAdapter = Adapter(object : OnItemViewClickListener {
        override fun onItemViewClick(movie: MovieInfo) {
            movieClickListener(movie)
        }
    })
    private val topRatedAdapter = Adapter(object : OnItemViewClickListener {
        override fun onItemViewClick(movie: MovieInfo) {
            movieClickListener(movie)
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerPopular.adapter = popularAdapter
        binding.recyclerToprated.adapter = topRatedAdapter
        loadMovieData("top_rated")
        loadMovieData("popular")
        /* viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getMovieDataFromServer()*/
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setMovieList(movieList:MutableList<MovieInfo>, genre: String) {
        if (genre == "popular") {
            popularAdapter.setDataSource(movieList)
        }
        if (genre == "top_rated") {
            topRatedAdapter.setDataSource(movieList)
        }
    }

    private fun loadMovieData(genre: String) {
        context?.let {
            it.startService(Intent(it, MovieService::class.java).apply {
                putExtra(GENRE_EXTRA, genre)
            })
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun dtoToMovieInfo(movieDTO: MovieDTO): MutableList<MovieInfo> {
        val movieInfoMutableList: MutableList<MovieInfo> = mutableListOf()
        for (movieDTO in movieDTO.results) {
            movieInfoMutableList.add(
                MovieInfo(
                    movieDTO.title,
                    movieDTO.overview,
                    movieDTO.vote_average
                )
            )
        }
        return movieInfoMutableList
    }

    private fun movieClickListener(movie: MovieInfo) {
        val manager = activity?.supportFragmentManager
        if (manager != null) {
            val bundle = Bundle()
            bundle.putParcelable(AboutMovieFragment.BUNDLE_EXTRA, movie)
            manager.beginTransaction()
                .replace(R.id.container, AboutMovieFragment.newInstance(bundle))
                .setTransition((FragmentTransaction.TRANSIT_FRAGMENT_FADE))
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }

    override fun onDestroy() {
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(loadResultsReceiver)
        }
        super.onDestroy()
    }

    companion object {
        fun newInstance() =
            ListerFragment()
    }
}

interface OnItemViewClickListener {
    fun onItemViewClick(movie: MovieInfo)
}

