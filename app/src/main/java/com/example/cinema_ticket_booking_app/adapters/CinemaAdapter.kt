package com.example.cinema_ticket_booking_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.models.Cinema

class CinemaAdapter (var listCinema: List<Cinema>): RecyclerView.Adapter<CinemaAdapter.CinemaViewHolder>() {

    class CinemaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgCinema: ImageView = itemView.findViewById(R.id.imgCinema)
        val txtCinemaName: TextView = itemView.findViewById(R.id.txtCinemaName)
        val txtLocation: TextView = itemView.findViewById(R.id.txtLocation)
        val txtSDT: TextView = itemView.findViewById(R.id.txtSDT)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_layout, parent, false)
        return CinemaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        val currentItem = listCinema[position]
        holder.imgCinema.setImageResource(currentItem.imgCinema)
        holder.txtCinemaName.text = currentItem.cinemaName
        holder.txtLocation.text = currentItem.location
        holder.txtSDT.text = currentItem.SDT
    }

    override fun getItemCount(): Int {
        return listCinema.size
    }
}