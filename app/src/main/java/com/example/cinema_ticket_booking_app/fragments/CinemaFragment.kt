package com.example.cinema_ticket_booking_app.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.activities.DetailMovieActivity
import com.example.cinema_ticket_booking_app.adapters.CinemaAdapter
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.FragmentCinemaBinding
import com.example.cinema_ticket_booking_app.models.Cinema
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CinemaFragment : Fragment(R.layout.fragment_cinema), CinemaAdapter.OnItemCinemaClickListener{
    private lateinit var binding: FragmentCinemaBinding
    private lateinit var cinemaAdapter: CinemaAdapter
    private lateinit var list: List<Cinema>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCinemaBinding.bind(view)

        fetchCinemas()

    }

    private fun fetchCinemas(){
        val call = ApiCall.service.getCinemas()
        call.enqueue(object : Callback<List<Cinema>> {
            override fun onResponse(call: Call<List<Cinema>>, response: Response<List<Cinema>>) {
                if(response.isSuccessful){
                    val rs = response.body()!!
                    list = rs
                    cinemaAdapter = CinemaAdapter(list, this@CinemaFragment)
                    binding.rcvCinema.adapter = cinemaAdapter
                    binding.rcvCinema.layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.VERTICAL,
                        false)
                }
            }
            override fun onFailure(call: Call<List<Cinema>>, t: Throwable) {
                Log.d("a", t.message.toString())
                Log.d("a", t.cause.toString())
            }
        })
    }

    override fun onCinemaItemClick(position: Int) {
        val item = list[position]
        val intent = Intent(this@CinemaFragment.context, DetailMovieActivity::class.java)
        intent.putExtra("movie_id", item.cinema_id)
        startActivity(intent)
    }
}