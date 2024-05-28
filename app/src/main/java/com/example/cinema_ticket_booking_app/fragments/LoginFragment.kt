package com.example.cinema_ticket_booking_app.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.activities.LoginActivity
import com.example.cinema_ticket_booking_app.activities.SignUpActivity
import com.example.cinema_ticket_booking_app.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentLoginBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener{
            val intent = Intent(this@LoginFragment.context, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener{
            val intent = Intent(this@LoginFragment.context, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}