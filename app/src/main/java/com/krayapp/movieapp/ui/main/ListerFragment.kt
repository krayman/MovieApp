package com.krayapp.movieapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.krayapp.movieapp.R
import com.krayapp.movieapp.model.MovieSource
import com.krayapp.movieapp.viewmodel.MainViewModel

class ListerFragment : Fragment() {
    private lateinit var data : MovieSource
    private lateinit var adapter:Adapter
    private lateinit var recyclerView:RecyclerView


    companion object {
        fun newInstance() = ListerFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}