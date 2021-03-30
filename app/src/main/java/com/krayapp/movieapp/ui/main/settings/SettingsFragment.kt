package com.krayapp.movieapp.ui.main.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.krayapp.movieapp.databinding.SettingsLayoutBinding
import com.krayapp.movieapp.ui.main.mainScreen.IS_ADULT_KEY
import com.krayapp.movieapp.ui.main.mainScreen.ListerFragment.Companion.adult

class SettingsFragment : Fragment() {
    private var _binding: SettingsLayoutBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SettingsLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFields()
    }

    companion object {
        fun newInstance() =
            SettingsFragment()
    }

    private fun saveAdultStatus(isAdult: Boolean) {
        activity?.let {
            with(it.getPreferences(Context.MODE_PRIVATE).edit()) {
                putBoolean(IS_ADULT_KEY, isAdult)
                apply()
            }
        }
    }

    private fun initFields() {
        binding.adultSwitch.setChecked(adult!!)
        binding.adultSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            adult = isChecked
            saveAdultStatus(adult!!)
        }
    }
}