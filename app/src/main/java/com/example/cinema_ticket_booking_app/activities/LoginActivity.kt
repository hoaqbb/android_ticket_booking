package com.example.cinema_ticket_booking_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.ActivityLoginBinding
import com.example.cinema_ticket_booking_app.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var username: String
    private lateinit var password: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            onClickLogin()
        }
    }

    private fun onClickLogin(){
        binding.btnLogin.setOnClickListener{
            username = binding.edtUsername.text.toString()
            password = binding.edtPassword.text.toString()
            val call = ApiCall.service.authenticate("\"$username\"")
            call.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val rs = response.body()!!
                        if (username == rs.username && password == rs.password ) {
                            Toast.makeText(this@LoginActivity, "Login", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@LoginActivity, "Fail", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "Fail", Toast.LENGTH_SHORT).show()
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