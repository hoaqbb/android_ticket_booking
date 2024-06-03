package com.example.cinema_ticket_booking_app.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinema_ticket_booking_app.adapters.SeatAdapter
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.ActivityBookTicketBinding
import com.example.cinema_ticket_booking_app.models.RoomSeat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookTicketActivity : AppCompatActivity(), SeatAdapter.OnItemClickListener {
    private lateinit var binding: ActivityBookTicketBinding
    private lateinit var seatAdapter: SeatAdapter
    private lateinit var list: List<RoomSeat>
    private lateinit var txtTotalAmount: TextView
    private lateinit var btnContinue: Button
    private lateinit var btnBack: ImageButton

    //mang tinh~ luu id cac ghe duoc chon
    object StaticArray{
        val listSelectedSeat = ArrayList<Int>(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        txtTotalAmount = binding.txtTotalAmount
        btnContinue = binding.btnContinue
        btnBack = binding.btnBack

//        intent.putExtra("show_id", currentItem.show_id)
//        intent.putExtra("movie_name", currentItem.movie.movie_name)
//        intent.putExtra("cinema_name", currentItem.cinema.cinema_name)
//        intent.putExtra("start_time", currentItem.start_time)

        //lay gia tri truyen tu showtimes fragment
        val showId = intent.getIntExtra("show_id", -1)
        val movieName = intent.getStringExtra("movie_name")
        val cinemaName = intent.getStringExtra("cinema_name")
        val startTime = intent.getStringExtra("start_time")

        binding.txtMovieName.text = movieName
        binding.txtCinemaName.text = cinemaName
        binding.txtShowtime.text = startTime


        fetchSeats(showId)

        btnContinue.setOnClickListener {
            val amount = txtTotalAmount.text
            //kiem tra nguoi dung da chon ve chua
            if(amount.length>1){
                val intent = Intent(this, InvoiceActivity::class.java)
                intent.putExtra("show_id", showId)
                intent.putExtra("amount", amount)
                startActivity(intent)
            }
        }

        btnBack.setOnClickListener { onBackPressed() }
    }

    private fun fetchSeats(show_id: Int){
        val call = ApiCall.service.getSeatShowById(show_id)
        call.enqueue(object : Callback<List<RoomSeat>> {
            override fun onResponse(call: Call<List<RoomSeat>>, response: Response<List<RoomSeat>>) {
                if(response.isSuccessful){
                    val rs = response.body()!!
                    list = rs
                    seatAdapter = SeatAdapter(list, this@BookTicketActivity)
                    binding.rcvSeat.adapter = seatAdapter
                    binding.rcvSeat.layoutManager = GridLayoutManager(
                        this@BookTicketActivity,
                        seatAdapter.itemCount/5,
                        GridLayoutManager.VERTICAL,
                        false)
                }
            }
            override fun onFailure(call: Call<List<RoomSeat>>, t: Throwable) { }
        })
    }

    //lay gia tien va id seat duoc chon
    @SuppressLint("SetTextI18n")
    override fun onItemClick(position: Int, isSelected: Boolean) {
        val item = list[position]
        if(isSelected) {
            //luu id seat vao mang neu duoc chon
            StaticArray.listSelectedSeat.add(item.seat_id!!)
            //kiem tra tong tien > 0
            if( txtTotalAmount.text.length>1) {
                //replace dau . trong chuoi va chuyen thanh Int + gia ve
                //roi chuyen ve lai chuoi format theo dang 140.000
                txtTotalAmount.text =
                    String.format("%,d", txtTotalAmount.text.toString().replace(".", "").toInt() + (item.price!!))
            } else{
                txtTotalAmount.text =
                    String.format("%,d", txtTotalAmount.text.toString().toInt() + (item.price!!))
            }
        } else {
            //loai id seat ra khoi mang neu ko dc chon nua
            if(StaticArray.listSelectedSeat.contains(item.seat_id))
                StaticArray.listSelectedSeat.remove(item.seat_id)
            if( txtTotalAmount.text.length>1) {
                txtTotalAmount.text =
                    String.format("%,d", txtTotalAmount.text.toString().replace(".", "").toInt() - (item.price!!))
            } else{
                txtTotalAmount.text =
                    String.format("%,d", txtTotalAmount.text.toString().toInt() - (item.price!!))
            }
        }
    }

    @Deprecated("Deprecated in Java",
        ReplaceWith("super.onBackPressed()", "androidx.appcompat.app.AppCompatActivity")
    )
    override fun onBackPressed() {
        super.onBackPressed()
        StaticArray.listSelectedSeat.clear()
    }
}