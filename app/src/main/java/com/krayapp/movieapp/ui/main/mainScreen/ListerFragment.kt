package com.krayapp.movieapp.ui.main.mainScreen

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.krayapp.movieapp.R
import com.krayapp.movieapp.databinding.MainFragmentBinding
import com.krayapp.movieapp.model.MovieDTO
import com.krayapp.movieapp.model.MovieInfo
import com.krayapp.movieapp.model.MovieLoader
import com.krayapp.movieapp.model.getScaredMovie
import com.krayapp.movieapp.ui.main.aboutMovie.AboutMovieFragment
import com.krayapp.movieapp.viewmodel.AppState
import com.krayapp.movieapp.viewmodel.MainViewModel

class ListerFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    @RequiresApi(Build.VERSION_CODES.N)
    private val onLoadListener: MovieLoader.MovieLoaderListener =
        object : MovieLoader.MovieLoaderListener {
            override fun onLoaded(movieDTO: MovieDTO) {
                setMovieList(movieDTO)
            }

            override fun onFailed(throwable: Throwable) {
            }

        }
    private val adapter = Adapter(object : OnItemViewClickListener {
        override fun onItemViewClick(movie: MovieInfo) {
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
        val listener = MovieLoader(onLoadListener)
        listener.loadMovieData()
        binding.recyclerAdventure.adapter = adapter
       /* viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getMovieDataFromServer()*/
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setMovieList(movieDTO: MovieDTO){
        adapter.setDataSource(dtoToMovieInfo(movieDTO))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                adapter.setDataSource(appState.movieData)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
            }
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
    companion object {
        fun newInstance() =
            ListerFragment()
    }
}
interface OnItemViewClickListener {
    fun onItemViewClick(movie: MovieInfo)
}

