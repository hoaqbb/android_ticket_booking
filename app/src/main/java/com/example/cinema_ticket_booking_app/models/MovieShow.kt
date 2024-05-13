package com.example.cinema_ticket_booking_app.models

import java.sql.Time

data class MovieShow(
    var show_id: Int,
    var movie: Movie,
    var cinema: Cinema,
    var room: CinemaRoom,
    var start_time: Time
)
