package com.krayapp.movieapp.ui.main.aboutMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krayapp.movieapp.databinding.AboutMovieBinding
import com.krayapp.movieapp.model.MovieInfo

class AboutMovieFragment : Fragment() {
    private var _binding: AboutMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AboutMovieBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.getParcelable<MovieInfo>(BUNDLE_EXTRA)
        if(movie != null){
            binding.title.text = movie.title
            binding.description.text = movie.description
            binding.rate.text = movie.rate.toString()
        }
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