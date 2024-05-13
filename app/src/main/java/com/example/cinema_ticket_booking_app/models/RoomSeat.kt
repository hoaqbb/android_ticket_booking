package com.example.cinema_ticket_booking_app.models

data class RoomSeat(
    var seat_id: Int,
    var room: CinemaRoom,
    var seat_name: String,
    var seat_status: Boolean,
    var price: Int
)
