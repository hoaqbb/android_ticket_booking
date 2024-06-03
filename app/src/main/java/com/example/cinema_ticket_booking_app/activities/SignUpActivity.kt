package com.example.cinema_ticket_booking_app.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
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

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.txtFullname.onFocusChangeListener = View.OnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.txtFullnameContainer.helperText = validFullname(binding.txtFullname.text.toString())
            }
        }

        binding.txtUsername.onFocusChangeListener = View.OnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.txtUsernameContainer.helperText = validUsername(binding.txtUsername.text.toString())
            }
        }

        binding.txtEmail.onFocusChangeListener = View.OnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.txtEmailContainer.helperText = validEmail(binding.txtEmail.text.toString())
            }
        }
        //gender?

        binding.txtPassword.onFocusChangeListener = View.OnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.txtPasswordContainer.helperText = validPassword(binding.txtPassword.text.toString())
            }
        }

        binding.txtConfirmPassword.onFocusChangeListener = View.OnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.txtConfirmPasswordContainer.helperText = validConfirmPassword(binding.txtPassword.text.toString())
            }
        }

        binding.btnSignUp.setOnClickListener {
            createAccount()
        }

        binding.txtLogin.setOnClickListener {
            finish()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createAccount(){
        val fullname = binding.txtFullname.text.toString()
        val username = binding.txtUsername.text.toString()
        val email = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()
//        val confirmPassword = binding.txtConfirmPassword.text.toString()

        val fullnameError = validFullname(fullname)
        val usernameError = validUsername(username)
        val emailError = validEmail(email)
        val passwordError = validPassword(password)
        val confirmPasswordError = validConfirmPassword(password)

        if(fullnameError == null && usernameError == null && emailError == null
            && passwordError == null && confirmPasswordError == null) {
            //kiem tra username da ton tai chua
            checkUsername(username) { usernameExisted ->
                if (!usernameExisted) {
                    val user = User(null, fullname, username,
                        password, email, 0)
                    val call = ApiCall.service.createAccount(user)
                    call.enqueue(object : Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if (response.isSuccessful) {
                                showAlertDialog("Account successfully created!")
                                finish()
                                //luu user session luon khoi dang nhap lai, hoac bat dang nhap luon
                                startActivity(Intent(this@SignUpActivity ,LoginActivity::class.java))

                            }
                        }
                        override fun onFailure(call: Call<User>?, t: Throwable?) {
                            Log.d("error", t?.message.toString())
                            Log.d("error", t?.cause.toString())
                            showAlertDialog("Account creation failed, please check your internet connection!")
                        }
                    })
                } else{
                    binding.txtUsernameContainer.helperText = "This username has been used!!!"
                }
            }
        }
    }

    //kiem tra username da ton tai chua
    private fun checkUsername(username: String, callback: (Boolean) -> Unit) {
        val call = ApiCall.service.authenticate("\"$username\"")
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                    val usernameExisted = response!!.isSuccessful && response.body() != null
                    callback(usernameExisted)
            }
            override fun onFailure(call: Call<User>?, t: Throwable?) {
                callback(false)
            }
        })
    }

    private fun validFullname(fullname: String): String?{
        if(fullname.trim().isEmpty())
            return "Fullname is required field."
            else if(fullname.trim().length < 3)
                return "Invalid fullname!"
        return null
    }

    private fun validUsername(username: String): String? {
        if(username.trim().isEmpty()){
            return "Username is required field."
        } else if(username.trim().length < 6)
            return "Username should be longer than 6 characters!"
        return null
    }

    private fun validEmail(email: String): String? {
        if(email.trim().isEmpty()) {
            return "Email is required field."
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return "Invalid Email Address!"
        }
        return null
    }

    private fun validPassword(password: String): String? {
        if(password.isEmpty()) {
            return "Password is required field."
        } else {
            if (password.length < 10) {
                return "Minimum 10 characters password"
            }
//            if (!password.matches(".*[A-Z].*".toRegex())) {
//                return "Must contain 1 upper-case character"
//            }
//            if (!password.matches(".*[@#\$%^&=+].*".toRegex())) {
//                return "Must contain 1 special character (@#\$%^&=+)"
//            }
            if (!password.matches(".*[0-9].*".toRegex())) {
                return "Must contain 1 number"
            }
        }
        return null
    }

    private fun validConfirmPassword(password: String): String? {
        val confirmPassword = binding.txtConfirmPassword.text.toString()
        if(confirmPassword.isEmpty()) {
            return "Confirm Password is required field."
        } else if (confirmPassword != password){
            return "Confirm password does not match password field!"
        }
        return null
    }

    private fun showAlertDialog(message: String) {
        val alertDialog = AlertDialog.Builder(this@SignUpActivity)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }
}