package com.example.cinema_ticket_booking_app.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.adapters.ShowtimesAdapter
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.FragmentShowtimesMovieBinding
import com.example.cinema_ticket_booking_app.models.Cinema
import com.example.cinema_ticket_booking_app.models.MovieShow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.ceil

class ShowtimesMovieFragment : Fragment(R.layout.fragment_showtimes_movie){
    private lateinit var binding: FragmentShowtimesMovieBinding
    private lateinit var spnCinema: Spinner
    private lateinit var rcvShowtimes: RecyclerView
    private lateinit var showtimesAdapter: ShowtimesAdapter
    private lateinit var txtTime: TextView

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentShowtimesMovieBinding.bind(view)
        spnCinema = binding.spnCinema
        rcvShowtimes = binding.rcvShowtimes
        txtTime = binding.txtTime

        val currentDate = LocalDate.now()
        val dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH)
        val dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH)

        val dayOfWeek = currentDate.format(dayOfWeekFormatter)
        val formattedDate = currentDate.format(dateFormatter)
        binding.txtTime.text = "$dayOfWeek, $formattedDate"
        //nhan id tu movie detail
        val bundle = arguments
        val movieId = bundle!!.getInt("id")

        fetchCinemaShowtimes(movieId)

    }


    // Lấy danh sách tên các rạp chiếu phim
    private fun fetchCinemaShowtimes(movieId: Int){
        val call = ApiCall.service.getCinemaShowingMovie(movieId)
        call.enqueue(object : Callback<List<Cinema>> {
            override fun onResponse(call: Call<List<Cinema>>, response: Response<List<Cinema>>) {
                if(response.isSuccessful){
                    val cinemas = response.body()!!
                    setupSpinner(movieId, cinemas)
                }
            }
            override fun onFailure(call: Call<List<Cinema>>, t: Throwable) {
                Log.d("a", t.message.toString())
                Log.d("a", t.cause.toString())
            }
        })
    }

    //lay cac danh sach xuat chieu cua phim theo cinema id
    private fun fetchShowtimesMovie(movieId: Int, cinemaId: Int){
        val call = ApiCall.service.getShowtimesMovie(movieId, cinemaId)
        call.enqueue(object : Callback<List<MovieShow>> {
            override fun onResponse(call: Call<List<MovieShow>>, response: Response<List<MovieShow>>) {
                if(response.isSuccessful){
                    val listMovieShowtimes = response.body()!!
                    showtimesAdapter = ShowtimesAdapter(listMovieShowtimes, requireContext())
                    rcvShowtimes.adapter = showtimesAdapter
                    rcvShowtimes.layoutManager = GridLayoutManager(
                        context,
                        1,
                        GridLayoutManager.HORIZONTAL,
                        false)
                }
            }
            override fun onFailure(call: Call<List<MovieShow>>, t: Throwable) {
                Log.d("a", t.message.toString())
                Log.d("a", t.cause.toString())
            }
        })
    }

    private fun setupSpinner(movieId: Int, cinemas: List<Cinema>) {
        val cinemaNames = cinemas.map { it.cinema_name }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cinemaNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnCinema.adapter = adapter

        spnCinema.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Lấy id của rạp chiếu phim đã chọn
                val selectedCinema = cinemas[position].cinema_id

                // Gọi API để lấy danh sách showtimes cho rạp đã chọn
                fetchShowtimesMovie(movieId, selectedCinema)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

}