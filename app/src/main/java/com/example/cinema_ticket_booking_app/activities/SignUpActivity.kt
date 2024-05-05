package com.example.cinema_ticket_booking_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cinema_ticket_booking_app.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

//        onClickEvent()
    }

//    private fun onClickEvent() {
//        binding.btnSignUp.setOnClickListener{
//            var fullName = binding.txtFullName.text.toString()
//            var email = binding.txtEmail.text.toString()
//            var checkedGender = binding.radio.checkedRadioButtonId.toString()
//            var password = binding.txtPassword.text.toString()
//            var confirmPassword = binding.txtConfirmPassword.text.toString()
//            var checkEmpty = true
//
//            if(checkEmpty) {
//                if (fullName.isEmpty()) {
//                    binding.txtFullName.error = "Please enter your full name"
//                    checkEmpty = false
//                }
//                if (email.isEmpty()) {
//                    binding.txtEmail.error = "Please enter your email"
//                    checkEmpty = false
//                }
//                //            if(checkedGender.isEmpty()){
//                //                binding.radio.e = "Please enter your password"
//                //            }
//                if (password.isEmpty()) {
//                    binding.txtPassword.error = "Please enter your password"
//                    checkEmpty = false
//                }
//                if (confirmPassword.isEmpty()) {
//                    binding.txtConfirmPassword.error = "Please enter your confirm password"
//                    checkEmpty = false
//                }
//            } else {
//                checkEmpty = true
//            }
//
//            if(checkEmpty){
//                if (fullName.length < 2 ) {
//                    binding.txtFullName.error = "Please enter your full name"
//                }
//                if (email.isEmpty()) {
//                    binding.txtEmail.error = "Please enter your email"
//                    checkEmpty = false
//                }
//                //            if(checkedGender.isEmpty()){
//                //                binding.radio.e = "Please enter your password"
//                //            }
//                if (password.isEmpty()) {
//                    binding.txtPassword.error = "Please enter your password"
//                    checkEmpty = false
//                }
//                if (confirmPassword.isEmpty()) {
//                    binding.txtConfirmPassword.error = "Please enter your confirm password"
//                    checkEmpty = false
//                }
//            }
//        }
//    }

}