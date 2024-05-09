package com.example.cinema_ticket_booking_app.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiCall {

    companion object {
        //http://192.168.1.6:3000/
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.8:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: ApiService = retrofit.create(ApiService::class.java)
    }


}