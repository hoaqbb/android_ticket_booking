package com.example.cinema_ticket_booking_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.ActivitySignUpBinding
import com.example.cinema_ticket_booking_app.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val txtFullnameContainer = binding.txtFullnameContainer
//        val txtFullname = binding.txtFullname

////////

/////////
//        onClickEvent()
//        binding.btnSignUp.setOnClickListener {
            fullnameFocusListener()
            usernameFocusListener()
            emailFocusListener()
            passwordFocusListener()
            confirmPasswordFocusListener()
//            createAccount()
//        }

        binding.txtLogin.setOnClickListener {
            finish()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    ////////////////
    private fun fullnameFocusListener() {
        binding.txtFullname.setOnFocusChangeListener { _, focused ->
            if (!focused)
            {
                binding.txtFullnameContainer.helperText = validFullname()
            }
        }
    }

    private fun validFullname(): String?
    {
        val fullname = binding.txtFullname.text?.trim()
        if(fullname!!.isEmpty()){
            return "Please enter your fullname!"
        }

        return null
    }


    private fun usernameFocusListener() {
        binding.txtUsername.setOnFocusChangeListener { _, focused ->
            if (!focused)
            {
                binding.txtUsernameContainer.helperText = validUsername()
            }
        }
    }

    private fun validUsername(): String?
    {
        val username = binding.txtUsername.text?.trim()

        return null
    }

    private fun emailFocusListener() {
        binding.txtEmail.setOnFocusChangeListener { _, focused ->
            if (!focused)
            {
                binding.txtEmailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String?
    {
        val email = binding.txtEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return "Invalid Email Address!"
        }
        return null
    }

    private fun passwordFocusListener() {
        binding.txtPassword.setOnFocusChangeListener { _, focused ->
            if (!focused)
            {
                binding.txtPasswordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String?
    {
        val passwordText = binding.txtPassword.text.toString()
        if (passwordText.length < 10)
        {
            return "Minimum 10 characters password"
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex()))
        {
            return "Must contain 1 upper-case character"
        }
        if (!passwordText.matches(".*[@#\$%^&=+].*".toRegex()))
        {
            return "Must contain 1 special character (@#\$%^&=+)"
        }
        if (!passwordText.matches(".*[0-9].*".toRegex()))
        {
            return "Must contain 1 number"
        }
        return null
    }

    private fun confirmPasswordFocusListener() {
        binding.txtConfirmPassword.setOnFocusChangeListener { _, focused ->
            if (!focused)
            {
                binding.txtConfirmPasswordContainer.helperText = validConfirmPassword(binding.txtPassword.text.toString())
            }
        }
    }

    private fun validConfirmPassword(password: String): String?
    {
        val confirmPasswordText = binding.txtConfirmPassword.text.toString()
        if (confirmPasswordText != password)
        {
            return "Confirm password does not match password field!"
        }
        return null
    }
}