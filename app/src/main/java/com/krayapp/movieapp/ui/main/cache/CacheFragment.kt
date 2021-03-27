package com.krayapp.movieapp.ui.main.cache

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.krayapp.movieapp.app.AppState
import com.krayapp.movieapp.databinding.FragmentCacheBinding
import com.krayapp.movieapp.viewmodel.CacheViewModel
import kotlinx.android.synthetic.main.fragment_cache.*

class CacheFragment : Fragment() {
    private var _binding: FragmentCacheBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CacheViewModel by lazy { ViewModelProvider(this).get(CacheViewModel::class.java) }
    private val adapter: AdapterCache by lazy { AdapterCache() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCacheBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cacheFragmentRecyclerview.adapter = adapter
        viewModel.cacheLiveData.observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getAllCache()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.cacheFragmentRecyclerview.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                adapter.setData(appState.movieData)
            }
            is AppState.Loading -> {
                binding.cacheFragmentRecyclerview.visibility = View.GONE
                binding.includedLoadingLayout.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.cacheFragmentRecyclerview.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = CacheFragment()
    }
}