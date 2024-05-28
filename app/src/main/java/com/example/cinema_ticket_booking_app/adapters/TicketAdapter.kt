package com.example.cinema_ticket_booking_app.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.models.Ticket
import com.squareup.picasso.Picasso

class TicketAdapter (private var listTicket: List<Ticket>): RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {
    class TicketViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgMovie: ImageView = itemView.findViewById(R.id.imgMovie)
        val txtTicketName: TextView = itemView.findViewById(R.id.txtTicketName)
        val txtCinemaName: TextView = itemView.findViewById(R.id.txtCinemaName)
        val txtTime: TextView = itemView.findViewById(R.id.txtTime)
        val txtSeatName: TextView = itemView.findViewById(R.id.txtSeatName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ticket_layout, parent, false)
        return TicketViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val currentItem = listTicket[position]
        Picasso.get().load(currentItem.movieShow?.movie?.movie_img).into(holder.imgMovie)
        holder.txtTicketName.text = currentItem.ticket_name
        holder.txtSeatName.text = currentItem.seat?.seat_name
        holder.txtCinemaName.text = currentItem.movieShow?.cinema?.cinema_name
        holder.txtTime.text = currentItem.movieShow?.start_time + " - " + currentItem.expiry_date
    }

    override fun getItemCount(): Int {
        return listTicket.size
    }
}