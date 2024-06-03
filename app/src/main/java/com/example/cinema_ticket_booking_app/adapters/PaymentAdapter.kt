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

class PaymentAdapter(var listPayment: List<Ticket>): RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {

    inner class PaymentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var txtBookingId: TextView = itemView.findViewById(R.id.txtBookingId)
        var imgMovie: ImageView = itemView.findViewById(R.id.imgMovie)
        var txtMovieName: TextView = itemView.findViewById(R.id.txtMovieName)
        var txtCinemaName: TextView = itemView.findViewById(R.id.txtCinemaName)
        var txtTime: TextView = itemView.findViewById(R.id.txtTime)
        var txtDate: TextView = itemView.findViewById(R.id.txtDate)
        var txtQuantity: TextView = itemView.findViewById(R.id.txtQuantity)
        var txtTotalAmount: TextView = itemView.findViewById(R.id.txtTotalAmount)
        var txtDateBooking: TextView = itemView.findViewById(R.id.txtDateBooking)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.payment_layout, parent, false)
        return PaymentViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val currentItem = listPayment[position]
        holder.apply {
            txtBookingId.text = currentItem.payment?.payment_id.toString()
            Picasso.get().load(currentItem.movieShow?.movie?.movie_img).into(holder.imgMovie)
            txtMovieName.text = currentItem.movieShow?.movie?.movie_name.toString()
            txtCinemaName.text = currentItem.movieShow?.cinema?.cinema_name.toString()
            txtTime.text = currentItem.movieShow?.start_time.toString()
            txtDate.text = currentItem.expiry_date.toString()
            txtQuantity.text = currentItem.payment?.total_ticket.toString()
            txtTotalAmount.text = String.format("%,d", currentItem.payment?.amount) + " ₫"
            txtDateBooking.text = currentItem.payment?.date
        }
    }

    override fun getItemCount(): Int {
        return listPayment.size
    }
}