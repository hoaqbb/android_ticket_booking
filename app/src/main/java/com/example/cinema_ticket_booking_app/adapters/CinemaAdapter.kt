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

class CinemaAdapter (private var listCinema: List<Cinema>, private var listener: OnItemCinemaClickListener): RecyclerView.Adapter<CinemaAdapter.CinemaViewHolder>() {
    class CinemaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgCinema: ImageView = itemView.findViewById(R.id.imgCinema)
        val txtCinemaName: TextView = itemView.findViewById(R.id.txtCinemaName)
        val txtAddress: TextView = itemView.findViewById(R.id.txtAddress)
        val txtSDT: TextView = itemView.findViewById(R.id.txtSDT)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cinema_layout, parent, false)
        return CinemaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        val currentItem = listCinema[position]
        Picasso.get().load(currentItem.cinema_img).into(holder.imgCinema)
        holder.txtCinemaName.text = currentItem.cinemaName
        holder.txtAddress.text = currentItem.address
        holder.txtSDT.text = currentItem.sdt
        holder.itemView.setOnClickListener{
            listener.onCinemaItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return listCinema.size
    }

    interface OnItemCinemaClickListener{
        fun onCinemaItemClick(position: Int)
    }
}