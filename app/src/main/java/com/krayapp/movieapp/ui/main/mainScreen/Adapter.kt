package com.krayapp.movieapp.ui.main.mainScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krayapp.movieapp.R
import com.krayapp.movieapp.model.MovieInfo
import kotlinx.android.synthetic.main.card_template.view.*

class Adapter(private var onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var movieData: List<MovieInfo> = listOf()

    fun setDataSource(movieData: MutableList<MovieInfo>) {
        this.movieData = movieData
        notifyDataSetChanged()
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_template, parent, false) as View
        )
    }

    override fun getItemCount(): Int =
        movieData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieData[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: MovieInfo) {
            itemView.findViewById<TextView>(R.id.full_card_template).title_template.text =
                movie.title
            itemView.findViewById<TextView>(R.id.full_card_template).description_template.text =
                movie.description
            itemView.findViewById<TextView>(R.id.full_card_template).title_template.text =
                movie.title
            itemView.setOnClickListener {
                onItemViewClickListener?.onItemViewClick(movie)
            }
        }
    }
}

