package com.krayapp.movieapp.ui.main.cache

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.krayapp.movieapp.R
import com.krayapp.movieapp.model.MovieInfo
import kotlinx.android.synthetic.main.card_template.view.*

class AdapterCache : RecyclerView.Adapter<AdapterCache.RecyclerItemViewHolder>() {

    private var data: MutableList<MovieInfo> = arrayListOf()

    fun setData(data: MutableList<MovieInfo>) {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_template, parent, false) as View
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }


    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind (data:MovieInfo){
                val img = itemView.findViewById<AppCompatImageView>(R.id.card4img).imageCard
                itemView.findViewById<TextView>(R.id.full_card_template).title_template.text =
                    data.title
                itemView.findViewById<TextView>(R.id.full_card_template).description_template.text =
                    data.description
                itemView.findViewById<TextView>(R.id.full_card_template).title_template.text =
                    data.title
                img.setImageResource(R.drawable.hell)
            }
    }
}
