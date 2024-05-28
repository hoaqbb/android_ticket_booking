package com.example.cinema_ticket_booking_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.models.Cinema
import com.squareup.picasso.Picasso

class BottomSliderAdapter (private var listImage: MutableList<Cinema>):
    RecyclerView.Adapter<BottomSliderAdapter.CinemaShowtimesViewHolder>() {

    class CinemaShowtimesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.image)
        val tittle: TextView = itemView.findViewById(R.id.txtTittle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaShowtimesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bottom_slider_layout, parent, false)
        return CinemaShowtimesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CinemaShowtimesViewHolder, position: Int) {
        val currentItem = listImage[position]
        Picasso.get().load(currentItem.cinema_img).into(holder.image)
        holder.tittle.text = currentItem.cinema_name
    }

    override fun getItemCount(): Int {
        return listImage.size
    }

}