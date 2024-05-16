package com.example.cinema_ticket_booking_app.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiCall {

    companion object {
        //Luu y: thay dia chi ip thanh dia chi ip may
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.88:3000/") //sua IP o day nha
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: ApiService = retrofit.create(ApiService::class.java)
    }


}