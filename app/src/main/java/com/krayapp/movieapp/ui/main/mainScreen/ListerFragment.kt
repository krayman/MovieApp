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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.krayapp.movieapp.R
import com.krayapp.movieapp.databinding.MainFragmentBinding
import com.krayapp.movieapp.model.MovieInfo
import com.krayapp.movieapp.ui.main.aboutMovie.AboutMovieFragment
import com.krayapp.movieapp.app.AppState
import com.krayapp.movieapp.viewmodel.MainViewModel

const val MOVIE_INTENT_FILTER = "DETAILS INTENT FILTER"
const val DETAILS_LOAD_RESULT_EXTRA = "LOAD RESULT"
const val GENRE_TYPE = "GENRE TYPE"
const val MOVIE_LIST_EXTRA = "MOVIE LIST EXTRA"
const val GENRE_EXTRA = "GENRE_EXTRA"
const val DETAILS_RESPONSE_SUCCESS_EXTRA = "RESPONSE SUCCESS"
const val API_KEY = "58cb0298f8a3d11c2c5b6afa5a8c7292"
const val POPULAR = "popular"
const val TOP_RATED = "top_rated"

const val IS_ADULT_KEY = "ADULT"

class ListerFragment : Fragment() {
    private val connectStatusReceiver: BroadcastReceiver = object : BroadcastReceiver() {
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
                    val mutableList: MutableList<MovieInfo> =
                        intent.getParcelableArrayListExtra<MovieInfo>(MOVIE_LIST_EXTRA)
                            ?.toMutableList()!!
                    setMovieList(mutableList)
                }
            }
        }
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModelPopular: MainViewModel
    private lateinit var viewModelTopRated: MainViewModel

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
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerPopular.adapter = popularAdapter
        binding.recyclerToprated.adapter = topRatedAdapter
        viewModelPopular = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModelPopular.liveData.observe(viewLifecycleOwner, Observer { renderData(it) })
        filmsAreAdultorNot()
        //viewModelPopular.getMovieDataFromServer(POPULAR, API_KEY)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setMovieList(movieList: MutableList<MovieInfo>){
            popularAdapter.setDataSource(movieList)
    }


    @SuppressLint("ShowToast")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.main.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                setMovieList(appState.movieData)
            }
            is AppState.Loading -> {
                binding.main.visibility = View.GONE
                binding.includedLoadingLayout.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.main.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                Toast.makeText(context, "Ошибка загрузки", Toast.LENGTH_LONG)
            }
        }
    }

    private fun filmsAreAdultorNot() {
        activity?.let {
            adult = it.getPreferences(Context.MODE_PRIVATE).getBoolean(IS_ADULT_KEY, false)
            viewModelPopular.getMovieDataFromServer(POPULAR, API_KEY)
        }

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
        var adult: Boolean? = null
        fun newInstance() =
            ListerFragment()
    }
}

interface OnItemViewClickListener {
    fun onItemViewClick(movie: MovieInfo)
}

