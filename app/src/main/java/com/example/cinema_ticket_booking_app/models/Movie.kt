package com.example.cinema_ticket_booking_app.models

data class Movie (
    var movie_id: Int,
    var movie_name: String,
    var status: Boolean,
    var movie_img: String,
    var description: String
    )