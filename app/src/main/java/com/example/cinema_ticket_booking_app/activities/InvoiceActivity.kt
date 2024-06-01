package com.example.cinema_ticket_booking_app.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.cinema_ticket_booking_app.activities.BookTicketActivity.StaticArray.listSelectedSeat
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.ActivityInvoiceBinding
import com.example.cinema_ticket_booking_app.models.*
import com.example.cinema_ticket_booking_app.session.UserSession
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class InvoiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInvoiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInvoiceBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //lay thong tin tu Book Ticket Activity truyen qua
        val showId = intent.getIntExtra("show_id", -1)
        val amount = intent!!.getStringExtra("amount")
        getMovieShowInfo(listSelectedSeat[0], showId)
        binding.txtTotalAmount.text = amount

        binding.btnPurchase.setOnClickListener{
            val confirmDialog = AlertDialog.Builder(this)
            with(confirmDialog){
                setTitle("Booking Confirmation")
                setMessage("Are you sure you want to book the ticket?")
                setPositiveButton("Yes") { dialog, _ ->
                    createPayment {
                        if(it>0){
                            createTicket(showId, it, listSelectedSeat)
                            changeStatusSeat(listSelectedSeat)
                        }
                    }
                    dialog.dismiss()
                }
                setNegativeButton("No"){ dialog, _ ->
                    dialog.dismiss()
                }
                show()
            }
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    //lay thong tin suat chieu
    private fun getMovieShowInfo(seatId: Int, showId: Int){
        val call = ApiCall.service.getMovieShowInfo(seatId, showId)
        call.enqueue(object : Callback<MovieShow> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<MovieShow>, response: Response<MovieShow>) {
                if(response.isSuccessful){
                    val rs = response.body()
                    Picasso.get().load(rs.movie!!.movie_img).into(binding.movieImage)
                    binding.txtMovieName.text = rs.movie!!.movie_name
                    binding.txtCinemaName.text = rs.cinema!!.cinema_name
                    binding.txtStartTime.text = rs.start_time
                    binding.txtEndTime.text = rs.end_time
                    binding.txtTime.text = rs.start_time
                    binding.txtRoomName.text = rs.room!!.room_name

                    //lay thong tin thu, ngay, thang, nam hien tai
                    val currentDate = LocalDate.now()
                    val dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH)
                    val dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH)

                    val dayOfWeek = currentDate.format(dayOfWeekFormatter)
                    val formattedDate = currentDate.format(dateFormatter)
                    binding.txtDate.text = " - $dayOfWeek, $formattedDate"
                    binding.txtQuantity.text = listSelectedSeat.size.toString()+"x"
                }
            }

            override fun onFailure(call: Call<MovieShow>, t: Throwable) { }
        })
    }

    //cap nhat trang thai cac ghe duoc chon
    private fun changeStatusSeat(listSelectedSeats: ArrayList<Int>){
        //chuyen ArrayList tu [a, b, c] thanh chuoi~ (a, b, c) cho phu hop cau query
        val list = "(" + listSelectedSeats.joinToString(", ") + ")"
            val call = ApiCall.service.updateStatusSeatById(list)
            call.enqueue(object : Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful){

                    }
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("Failed", t.message.toString())
                    Log.d("Failed", t.cause.toString())
                }
            })
    }

    //tao payment
    private fun createPayment(callback: (Int) -> Unit){
        val amount = binding.txtTotalAmount.text.toString().toInt()
        val totalTicket = listSelectedSeat.size
        Log.d("size", totalTicket.toString())
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val user = UserSession.loadUserSession(this)

        val payment = Payment(null, user, amount, totalTicket, current.format(formatter))
        val call = ApiCall.service.createPayment(payment)
        call.enqueue(object : Callback<Int>{
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if(response.isSuccessful){
                    //lay ve id cua payment vua tao
                    val paymentInsertedId = response.body()
                    callback.invoke(paymentInsertedId)
                }
            }
            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.d("Failed", t.message.toString())
                Log.d("Failed", t.cause.toString())
            }
        })
    }

    //tao danh sach ticket dua tren danh sach id ghe da chon va id payment vua tao
    private fun listTicket(showId: Int, paymentId: Int, listSelectedSeats: ArrayList<Int>): MutableList<Ticket>{
        val mutableListSeat = mutableListOf<Ticket>()
        for(item in listSelectedSeats){
            val seat = RoomSeat(item, null, null, null, null)
            val user = UserSession.loadUserSession(this)
            val movieShow = MovieShow(showId, null, null, null, null, null)
            val payment = Payment(paymentId, null, null, null, null)
            val ticketName = binding.txtMovieName.text.toString()

            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val expiryDate = binding.txtEndTime.text.toString() + " " + current.format(formatter)

            val ticket = Ticket(null, ticketName, seat, user, movieShow, expiryDate, payment)
            mutableListSeat.add(ticket)
        }
        return mutableListSeat
    }

    //insert ticket vao csdl
    private fun createTicket(showId: Int, paymentId: Int, listSelectedSeats: ArrayList<Int>){
        val call = ApiCall.service.createTicket(listTicket(showId, paymentId, listSelectedSeats))
        call.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful){
                    finish()
                    val intent = Intent(this@InvoiceActivity, ReceiptActivity::class.java)
//                    val intent = Intent(this@InvoiceActivity, MainActivity::class.java)
                    intent.putExtra("payment_id", paymentId)
                    startActivity(intent)
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("Error", t.message.toString())
                Log.d("Error", t.cause.toString())
            }
        })
    }
}