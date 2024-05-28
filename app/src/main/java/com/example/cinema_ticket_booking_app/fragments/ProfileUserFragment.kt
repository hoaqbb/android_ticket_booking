package com.example.cinema_ticket_booking_app.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.activities.InfoUserActivity
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.FragmentProfileUserBinding
import com.example.cinema_ticket_booking_app.models.User
import com.example.cinema_ticket_booking_app.session.UserSession
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileUserFragment : Fragment(R.layout.fragment_profile_user) {
    private lateinit var binding: FragmentProfileUserBinding
    private lateinit var txtFullname: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentProfileUserBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        txtFullname = binding.txtFullname

        //lay user_id tu shared reference
        val userId = UserSession.loadUserSession(requireContext()).user_id
        binding.ticket.setOnClickListener {
            val intent = Intent(requireContext(), InfoUserActivity::class.java)
            intent.putExtra("title", "Booking History")
            startActivity(intent)
        }

        binding.payment.setOnClickListener {
            val intent = Intent(requireContext(), InfoUserActivity::class.java)
            intent.putExtra("title", "Payment History")
            startActivity(intent)
        }

        getUserInfo(userId!!)
    }

    private fun getUserInfo(userId: Int){
        val call = ApiCall.service.getInfoUser(userId)
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful){
                    val rs = response.body()!!
                    txtFullname.text = rs.fullname
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("a", t.message.toString())
                Log.d("a", t.cause.toString())
            }
        })
    }
}