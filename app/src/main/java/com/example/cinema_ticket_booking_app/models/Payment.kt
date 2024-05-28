package com.example.cinema_ticket_booking_app.models

data class Payment (
    var payment_id: Int?,
    var user: User?,
    var amount: Int?,
    val total_ticket: Int?,
    var date: String?
)
