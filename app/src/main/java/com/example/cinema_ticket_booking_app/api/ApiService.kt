package com.example.cinema_ticket_booking_app.api

import com.example.cinema_ticket_booking_app.models.Cinema
import com.example.cinema_ticket_booking_app.models.Movie
import com.example.cinema_ticket_booking_app.models.User
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.net.CacheRequest

interface ApiService {
    ////http://192.168.1.6:3000/movie/api/movies?status=true
    @GET("movie/api/movies")
    fun getData(@Query("status") status: String): Call<List<Movie>>

    @GET("movie/api/movie-by-id")
    fun getMovieById(@Query("movie_id") movie_id: Int): Call<Movie>

    @GET("cinema/api/cinemas")
    fun getCinemas(): Call<List<Cinema>>

    @GET("user/api/authenticate")
    fun authenticate(@Query("username") username: String): Call<User>

    @POST("create-user")
    fun createAccount(@Body requestBody: RequestBody): Call<ResponseBody>
}