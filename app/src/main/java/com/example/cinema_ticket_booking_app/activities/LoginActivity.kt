package com.example.cinema_ticket_booking_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.adapters.MovieAdapter
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.ActivityLoginBinding
import com.example.cinema_ticket_booking_app.models.Movie
import com.example.cinema_ticket_booking_app.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickLogin()
    }

    private fun onClickLogin(){
        binding.btnLogin.setOnClickListener{
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()
            val call = ApiCall.service.authenticate(username)
            call.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val rs = response.body()!!
                        if (rs.password == password) {
                            Log.d("login", "Login success")
                        }
                    }
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("a", t.message.toString())
                    Log.d("a", t.cause.toString())
                }
            })
        }
    }
}