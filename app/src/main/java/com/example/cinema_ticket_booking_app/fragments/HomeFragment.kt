package com.example.cinema_ticket_booking_app.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.activities.DetailMovieActivity
import com.example.cinema_ticket_booking_app.adapters.MovieAdapter
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.FragmentHomeBinding
import com.example.cinema_ticket_booking_app.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home), MovieAdapter.OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var imageSlider: ImageSlider
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var list: List<Movie>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.bind(view)
        imageSlider = binding.slider
        slideShow(imageSlider)

        //Mac dinh hien thi On showing
        fetchMovies(true)
        //Su kien click tren Now showing
        binding.txtNowShowing.setOnClickListener{
            fetchMovies(true)
        }
        //Su kien click tren Comming soon
        binding.txtCommingSoon.setOnClickListener{
            fetchMovies(false)
        }
    }

    private fun slideShow(imageSlider: ImageSlider){
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("https://bom.so/QoKB2w"))
        imageList.add(SlideModel("https://bom.so/N4ujZO"))
        imageList.add(SlideModel("https://bom.so/yH6X9U"))

        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
    }

    //Doc phim tu api theo trang thai
    //true: Now showing, false: Comming soon
    private fun fetchMovies(status: Boolean){
        val call = ApiCall.service.getData("$status")
        call.enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if(response.isSuccessful){
                    val rs = response.body()!!
                    list = rs
                        movieAdapter = MovieAdapter(list, this@HomeFragment)
                        binding.rcvMovie.adapter = movieAdapter
                        binding.rcvMovie.layoutManager = GridLayoutManager(
                            context,
                            2,
                            GridLayoutManager.VERTICAL,
                            false)
                }
            }
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.d("a", t.message.toString())
                Log.d("a", t.cause.toString())
            }
        })
    }

    //Su kien click movie
    override fun onItemClick(position: Int) {
        val item = list[position]
        val intent = Intent(this@HomeFragment.context, DetailMovieActivity::class.java)
        intent.putExtra("movie_id", item.movie_id)
        startActivity(intent)
    }
}