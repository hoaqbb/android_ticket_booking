package com.example.cinema_ticket_booking_app.adapters

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.models.RoomSeat

class SeatAdapter(var listSeat: List<RoomSeat>, private val listener: OnItemClickListener):
    RecyclerView.Adapter<SeatAdapter.SeatViewHolder>() {
    class SeatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtNameSeat: TextView = itemView.findViewById(R.id.txtNameSeat)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.seat_layout, parent, false)
        return SeatViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        val currentItem = listSeat[position]
        holder.txtNameSeat.text = currentItem.seat_name
        if(currentItem.seat_status!!>0){
            holder.txtNameSeat.setBackgroundResource(R.color.light_gray)
        } else{
            //to mau ghe duoc chon
            holder.txtNameSeat.setOnClickListener{
                val getColor = holder.txtNameSeat.background as ColorDrawable
                val colorId = getColor.color
                if(colorId == -1){
                    listener.onItemClick(position, true)
                    holder.txtNameSeat.setBackgroundResource(R.color.light_orange)
                } else {
                    listener.onItemClick(position, false)
                    holder.txtNameSeat.setBackgroundResource(R.color.white)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listSeat.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, isSelected: Boolean)
    }
}