package com.example.cinema_ticket_booking_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.models.Movie

class MovieAdapter (var listMovie: List<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgMovie: ImageView = itemView.findViewById(R.id.imgMovie)
        val txtMovieName: TextView = itemView.findViewById(R.id.txtMovieName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_layout, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = listMovie[position]
        holder.imgMovie.setImageResource(currentItem.imgMovie)
        holder.txtMovieName.text = currentItem.movieName
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}