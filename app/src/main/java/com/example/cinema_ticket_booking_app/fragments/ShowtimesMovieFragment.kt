package com.example.cinema_ticket_booking_app.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.FragmentShowtimesMovieBinding
import com.example.cinema_ticket_booking_app.models.Movie
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowtimesMovieFragment : Fragment(R.layout.fragment_showtimes_movie) {
    private lateinit var binding: FragmentShowtimesMovieBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentShowtimesMovieBinding.bind(view)


    }

//    private fun getMovieById(movie_id: Int){
//        val call = ApiCall.service.getMovieById(movie_id)
//        call.enqueue(object : Callback<Movie> {
//            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
//                if(response.isSuccessful){
//                    val movie = response.body()!! //luu y tra ve doi tuong chu ko phai mang doi tuong
//                    binding.txtMovieName.text = movie.movie_name
//                    Picasso.get().load(movie.movie_img).into(binding.imgMovie)
//                    binding.txtDuration.text = movie.duration.toString()
//                    binding.txtReleaseDate.text = movie.release_date
//
//                }
//            }
//            override fun onFailure(call: Call<Movie>, t: Throwable) {
//                Log.d("a", t.message.toString())
//                Log.d("a", t.cause.toString())
//            }
//        })
//    }
}