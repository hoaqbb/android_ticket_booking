package com.example.cinema_ticket_booking_app.api

import com.example.cinema_ticket_booking_app.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {
    @GET("movie/api/movies")
    fun getMovies(@Query("status") status: String): Call<List<Movie>>

    @GET("movie/api/movie-by-id")
    fun getMovieById(@Query("movie_id") movie_id: Int): Call<Movie>

    @GET("cinema/api/cinemas")
    fun getCinemas(): Call<List<Cinema>>

    @GET("movieshow/api/cinema-showing-movie")
    fun getCinemaShowingMovie(@Query("movie_id") movie_id: Int): Call<List<Cinema>>

    @GET("movieshow/api/showtimes-movie")
    fun getShowtimesMovie(
        @Query("movie_id") movie_id: Int,
        @Query("cinema_id") cinema_id: Int
    ): Call<List<MovieShow>>

    @GET("user/api/authenticate")
    fun authenticate(@Query("username") username: String): Call<User>

    @GET("user/api/get-user-info")
    fun getInfoUser(@Query("user_id") user_id: Int): Call<User>

    @POST("user/api/create-account")
    fun createAccount(@Body user: User): Call<User>

    @GET("roomseat/api/seats-in-a-movie-show")
    fun getSeatShowById(@Query("show_id") show_id: Int): Call<List<RoomSeat>>

    @GET("roomseat/api/get-movie-show-info")
    fun getMovieShowInfo(
        @Query("seat_id") seat_id: Int,
        @Query("show_id") show_id: Int
    ): Call<MovieShow>

    @PUT("roomseat/api/update-status-seat")
    fun updateStatusSeatById(@Query("seat_id") seat_id: String): Call<String>

    //tra ve payment_id vua insert
    @POST("payment/api/create-payment")
    fun createPayment(@Body payment: Payment): Call<Int>
    @GET("payment/api/get-payment-info-by-id")
    fun getPaymentInfoById(@Query ("payment_id") payment_id: Int): Call<Ticket>

    @GET("payment/api/get-payments-by-user-id")
    fun getPaymentsByUserId(@Query("user_id") user_id: Int): Call<List<Ticket>>

    @POST("ticket/api/create-ticket")
    fun createTicket(
        @Body listTicket: MutableList<Ticket>
    ): Call<String>

    @GET("ticket/api/get-tickets-by-user-id")
    fun getTicketsByUserId(@Query("user_id") user_id: Int): Call<List<Ticket>>
}