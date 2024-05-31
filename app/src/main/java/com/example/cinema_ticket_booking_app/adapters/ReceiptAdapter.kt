package com.example.cinema_ticket_booking_app.adapters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cinema_ticket_booking_app.databinding.ActivityReceiptBinding

class ReceiptAdapter : AppCompatActivity() {
    private lateinit var binding: ActivityReceiptBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}