package com.example.cinema_ticket_booking_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.ActivityDetailMovieBinding
import com.example.cinema_ticket_booking_app.models.Movie
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movie_id = intent.getIntExtra("movie_id", -1)

        getMovieById(movie_id)

    }
    private fun getMovieById(movie_id: Int){
        val call = ApiCall.service.getMovieById(movie_id)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if(response.isSuccessful){
                    //luu y tra ve doi tuong chu ko phai mang doi tuong
                    val movie = response.body()!!
                    binding.txtMovieName.text = movie.movie_name
                    Picasso.get().load(movie.movie_img).into(binding.imgMovie)
                    binding.txtDuration.text = movie.description
                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("a", t.message.toString())
                Log.d("a", t.cause.toString())
            }
        })
    }
}