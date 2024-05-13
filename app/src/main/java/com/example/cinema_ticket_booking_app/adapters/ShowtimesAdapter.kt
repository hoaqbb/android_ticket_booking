package com.example.cinema_ticket_booking_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.models.Movie
import com.squareup.picasso.Picasso

class ShowtimesAdapter (var listMovie: List<Movie>, private val listener: OnItemClickListener):
    RecyclerView.Adapter<ShowtimesAdapter.ShowtimesViewHolder>() {

    class ShowtimesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        val imgMovie: ImageView = itemView.findViewById(R.id.imgMovie)
//        val txtMovieName: TextView = itemView.findViewById(R.id.txtMovieName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowtimesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.showtimes_layout, parent, false)
        return ShowtimesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowtimesViewHolder, position: Int) {
        val currentItem = listMovie[position]
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}