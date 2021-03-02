package com.krayapp.movieapp.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krayapp.movieapp.model.MovieSource
import com.krayapp.movieapp.model.MovieInfo

class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {
    private lateinit var dataSource: MovieSource

    fun setDataSource(dataSource : MovieSource) {
        this.dataSource = dataSource
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

    }
interface MyClickListener {
    fun onItemClick(position: Int, movieInfo: MovieInfo)
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}
