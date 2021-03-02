package com.krayapp.movieapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.krayapp.movieapp.databinding.CardTemplateBinding
import com.krayapp.movieapp.model.MovieSource
import com.krayapp.movieapp.viewmodel.MainViewModel

class ListerFragment : Fragment() {
    private lateinit var data: MovieSource
    private lateinit var adapter: Adapter
    private lateinit var recyclerView: RecyclerView

    private var _binding: CardTemplateBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ListerFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CardTemplateBinding.inflate(inflater, container, false)
        val view = binding.root
        initViews()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun initViews() {
        binding.fullCard.setOnClickListener {
            Toast.makeText(context, "MessageText", Toast.LENGTH_SHORT).show()
        }
    }

}