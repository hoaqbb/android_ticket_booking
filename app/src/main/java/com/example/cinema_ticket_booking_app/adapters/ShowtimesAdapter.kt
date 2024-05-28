package com.example.cinema_ticket_booking_app.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.activities.BookTicketActivity
import com.example.cinema_ticket_booking_app.activities.LoginActivity
import com.example.cinema_ticket_booking_app.models.MovieShow
import com.example.cinema_ticket_booking_app.session.UserSession

class ShowtimesAdapter (var listMovieShow: List<MovieShow>, var context: Context):
    RecyclerView.Adapter<ShowtimesAdapter.ShowtimesViewHolder>() {

    class ShowtimesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtShowtime: TextView = itemView.findViewById(R.id.txtShowtime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowtimesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.showtimes_layout, parent, false)
        return ShowtimesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowtimesViewHolder, position: Int) {
        val currentItem = listMovieShow[position]
        holder.txtShowtime.text = currentItem.start_time
        holder.itemView.setOnClickListener{
            //kiem tra ng dung da dang nhap chua
            //true: chuyen sang man hinh dat ve
            if(UserSession.loadUserSession(context).user_id!!>0){
                val intent = Intent(context, BookTicketActivity::class.java)
                intent.putExtra("show_id", currentItem.show_id)
                intent.putExtra("movie_name", currentItem.movie?.movie_name)
                intent.putExtra("cinema_name", currentItem.cinema?.cinema_name)
                intent.putExtra("start_time", currentItem.start_time)
                context.startActivity(intent)
            } else {
                //false: yeu cau dang nhap
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        return listMovieShow.size
    }

}