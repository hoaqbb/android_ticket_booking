package com.example.cinema_ticket_booking_app.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.ActivityLoginBinding
import com.example.cinema_ticket_booking_app.models.User
import com.example.cinema_ticket_booking_app.session.UserSession
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.edtUsername.onFocusChangeListener = View.OnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.txtUsernameContainer.helperText = validUsername(binding.edtUsername.text.toString())
            }
        }

        binding.edtPassword.onFocusChangeListener = View.OnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.txtPasswordContainer.helperText = validPassword(binding.edtPassword.text.toString())
            }
        }

        binding.btnLogin.setOnClickListener {
            onClickLogin()
        }

        binding.txtSignUp.setOnClickListener {
            finish()
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onClickLogin(){
        val username = binding.edtUsername.text.toString()
        val password = binding.edtPassword.text.toString()

        val usernameError = validUsername(username)
        val passwordError = validPassword(password)

        binding.txtUsernameContainer.helperText = usernameError
        binding.txtPasswordContainer.helperText = passwordError

        //kiem tra username va password co rong ko
        if (usernameError == null && passwordError == null) {
            val call = ApiCall.service.authenticate("\"$username\"")
            call.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val rs = response.body()!!
                        if (username == rs.username && password == rs.password) {
                            UserSession.saveUserSession(this@LoginActivity, rs.user_id!!)
                            finish()
                        } else {
                            showAlertDialog("Your username or password is incorrect!!!")
                        }
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    showAlertDialog("Can not login, please check your internet connection!!!")
                }
            })
        }
    }

    private fun validUsername(username: String): String? {
        if(username.trim().isEmpty()){
            return "Please enter username!"
        }
        return null
    }

    private fun validPassword(password: String): String? {
        if(password.isEmpty()){
            return "Please enter password!"
        }
        return null
    }

    private fun showAlertDialog(message: String) {
        val alertDialog = AlertDialog.Builder(this@LoginActivity)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }
}