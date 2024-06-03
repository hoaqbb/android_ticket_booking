package com.example.cinema_ticket_booking_app.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.ActivityReceiptBinding
import com.example.cinema_ticket_booking_app.models.Ticket
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReceiptActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReceiptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val paymentId = intent.getIntExtra("payment_id", -1)


        getInfoPayment(paymentId)

        binding.btnOk.setOnClickListener {
            BookTicketActivity.StaticArray.listSelectedSeat.clear()
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getInfoPayment(paymentId: Int) {
        val call = ApiCall.service.getPaymentInfoById(paymentId)
        call.enqueue(object : Callback<Ticket>{
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Ticket>, response: Response<Ticket>) {
                if(response.isSuccessful){
                    val paymentInfo = response.body()
                    binding.txtBookingId.text = paymentInfo.payment?.payment_id.toString()
                    binding.txtMovieName.text = paymentInfo.ticket_name
                    Picasso.get().load(paymentInfo.movieShow?.movie?.movie_img).into(binding.imgMovie)

                    binding.txtCinemaName.text = paymentInfo.movieShow?.cinema?.cinema_name
                    binding.txtTime.text = paymentInfo.movieShow?.start_time

                    val current = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                    binding.txtDate.text = current.format(formatter)

                    binding.txtQuantity.text = paymentInfo.payment?.total_ticket.toString()
                    binding.txtTotalAmount.text = String.format("%,d", paymentInfo.payment?.amount) + " ₫"
                    binding.txtDateBooking.text = paymentInfo.payment?.date
                }
            }

            override fun onFailure(call: Call<Ticket>, t: Throwable) {
                Log.d("Fail", t.message.toString())
                Log.d("Fail", t.cause.toString())
            }
        })
    }


}