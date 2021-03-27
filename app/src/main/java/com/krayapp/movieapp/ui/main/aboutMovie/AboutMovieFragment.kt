package com.krayapp.movieapp.ui.main.aboutMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.krayapp.movieapp.R
import com.krayapp.movieapp.databinding.AboutMovieBinding
import com.krayapp.movieapp.model.MovieInfo
import com.krayapp.movieapp.viewmodel.MainViewModel
import com.squareup.picasso.Picasso

class AboutMovieFragment : Fragment() {
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private var _binding: AboutMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AboutMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.getParcelable<MovieInfo>(BUNDLE_EXTRA)
        if (movie != null) {
            setMovieInfo(movie)
        }
    }

    private fun setMovieInfo(movieInfo: MovieInfo) {
        saveCityCache(movieInfo)
        val img = view?.findViewById<AppCompatImageView>(R.id.poster)
        binding.title.text = movieInfo.title
        binding.description.text = movieInfo.description
        binding.rate.text = movieInfo.rate.toString()
        Picasso
            .get()
            .load("https://image.tmdb.org/t/p/w500/${movieInfo.imagePath}")
            .into(img)
    }

    private fun saveCityCache(movieInfo: MovieInfo) {
        viewModel.saveMovieToDB(
            MovieInfo(
                movieInfo.title,
                movieInfo.description,
                movieInfo.rate,
                movieInfo.imagePath
            )
        )
    }

    companion object {
        const val BUNDLE_EXTRA = "weather"
        fun newInstance(bundle: Bundle): AboutMovieFragment {
            val fragment = AboutMovieFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}