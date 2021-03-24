package com.krayapp.movieapp.ui.main.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krayapp.movieapp.databinding.AboutMovieBinding
import com.krayapp.movieapp.databinding.SettingsLayoutBinding
import com.krayapp.movieapp.ui.main.mainScreen.ListerFragment

class SettingsFragment:Fragment() {
    private var _binding: SettingsLayoutBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SettingsLayoutBinding.inflate(inflater, container,false)
        return binding.root
    }

    companion object {
        fun newInstance() =
            SettingsFragment()
    }
}