package com.example.cinema_ticket_booking_app.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema_ticket_booking_app.adapters.CinemaAdapter
import com.example.cinema_ticket_booking_app.databinding.FragmentCinemaBinding
import com.example.cinema_ticket_booking_app.models.Cinema


class CinemaFragment : Fragment() {
    private lateinit var binding: FragmentCinemaBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCinemaBinding.bind(view)

//        val list = mutableListOf<Cinema>()
//
//
//        val adapter = CinemaAdapter(list)
//
//        binding.rcvLocation.adapter = adapter
//        binding.rcvLocation.layoutManager = LinearLayoutManager(
//            context,
//            LinearLayoutManager.HORIZONTAL,
//            false)
    }
}