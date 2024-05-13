package com.example.cinema_ticket_booking_app.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.FragmentInfoMovieBinding
import com.example.cinema_ticket_booking_app.models.Movie
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoMovieFragment : Fragment(R.layout.fragment_info_movie){
    private lateinit var binding: FragmentInfoMovieBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentInfoMovieBinding.bind(view)

        //nhan movie_id tu detail activity
        val bundle = arguments
        val movieId = bundle!!.getInt("id")
        getInfoMovie(movieId)

    }

    private fun getInfoMovie(movie_id: Int){
        val call = ApiCall.service.getMovieById(movie_id)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if(response.isSuccessful){
                    val movie = response.body()!! //luu y tra ve doi tuong chu ko phai mang doi tuong
                    binding.txtMovieDes.text = movie.description
                    binding.txtActors.text = movie.actors
                    binding.txtDirectors.text = movie.directors

                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("a", t.message.toString())
                Log.d("a", t.cause.toString())
            }
        })
    }


}