package com.example.cinema_ticket_booking_app.models

data class User(
    var user_id: Int?,
    var fullname: String?,
    var username: String?,
    var password: String?,
    var email: String?,
    var gender: Byte?
)
