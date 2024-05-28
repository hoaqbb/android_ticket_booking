package com.example.cinema_ticket_booking_app.models

data class Ticket(
    var ticket_id: Int?,
    var ticket_name: String?,
    var seat: RoomSeat?,
    var user: User?,
    var movieShow: MovieShow?,
    var expiry_date: String?,
    var payment: Payment?
)
