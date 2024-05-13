package com.example.cinema_ticket_booking_app.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.activities.LoginActivity
import com.example.cinema_ticket_booking_app.activities.SignUpActivity
import com.example.cinema_ticket_booking_app.databinding.FragmentAccountBinding


class AccountFragment : Fragment(R.layout.fragment_account) {
    private lateinit var binding: FragmentAccountBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAccountBinding.bind(view)

        binding.btnLogin.setOnClickListener{
            val intent = Intent(this@AccountFragment.context, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener{
            val intent = Intent(this@AccountFragment.context, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
}