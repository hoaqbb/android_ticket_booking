package com.example.cinema_ticket_booking_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cinema_ticket_booking_app.databinding.ActivityDetailMovieBinding

class DetailCinemaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}