package com.example.cinema_ticket_booking_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_ticket_booking_app.adapters.PaymentAdapter
import com.example.cinema_ticket_booking_app.adapters.TicketAdapter
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.ActivityInfoUserBinding
import com.example.cinema_ticket_booking_app.models.Ticket
import com.example.cinema_ticket_booking_app.session.UserSession
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoUserBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var txtTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInfoUserBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        txtTitle = binding.txtTitle
        recyclerView = binding.recycler

        val title = intent.getStringExtra("title")
        txtTitle.text = title

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        if(title == "Booking History"){
            fetchTickets()
        } else{
            getListPayment()
        }

    }

    private fun fetchTickets(){
        val userId = UserSession.loadUserSession(this@InfoUserActivity).user_id
        val call = ApiCall.service.getTicketsByUserId(userId!!)

        call.enqueue(object : Callback<List<Ticket>>{
            override fun onResponse(call: Call<List<Ticket>>, response: Response<List<Ticket>>) {
                if(response.isSuccessful){
                    val listTicket = response.body()
                    val adapter = TicketAdapter(listTicket)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(
                        this@InfoUserActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                }
            }

            override fun onFailure(call: Call<List<Ticket>>, t: Throwable) {}
        })
    }

    private fun getListPayment() {
        val userId = UserSession.loadUserSession(this@InfoUserActivity).user_id
        val call = ApiCall.service.getPaymentsByUserId(userId!!)
        call.enqueue(object : Callback<List<Ticket>>{
            override fun onResponse(call: Call<List<Ticket>>, response: Response<List<Ticket>>) {
                if(response.isSuccessful){
                    val listPayment = response.body()
                    val adapter = PaymentAdapter(listPayment)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(
                        this@InfoUserActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                }
            }

            override fun onFailure(call: Call<List<Ticket>>, t: Throwable) { }
        })
    }
}