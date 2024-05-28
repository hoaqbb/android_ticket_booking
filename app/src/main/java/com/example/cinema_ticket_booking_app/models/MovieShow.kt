package com.example.cinema_ticket_booking_app.models

data class MovieShow(
    var show_id: Int?,
    var movie: Movie?,
    var cinema: Cinema?,
    var room: CinemaRoom?,
    var start_time: String?,
    var end_time: String?
)
