package com.krayapp.movieapp.ui.main.aboutMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.krayapp.movieapp.R
import com.krayapp.movieapp.databinding.AboutMovieBinding
import com.krayapp.movieapp.model.MovieInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_template.view.*

class AboutMovieFragment : Fragment() {
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
        if(movie != null){
            val img = view.findViewById<AppCompatImageView>(R.id.poster)
            binding.title.text = movie.title
            binding.description.text = movie.description
            binding.rate.text = movie.rate.toString()
            Picasso
                .get()
                .load("https://image.tmdb.org/t/p/w500/${movie.imagePath}")
                .into(img)
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