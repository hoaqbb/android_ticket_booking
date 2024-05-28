package com.example.cinema_ticket_booking_app.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.activities.DetailMovieActivity
import com.example.cinema_ticket_booking_app.adapters.BottomSliderAdapter
import com.example.cinema_ticket_booking_app.adapters.MovieAdapter
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.FragmentHomeBinding
import com.example.cinema_ticket_booking_app.models.Cinema
import com.example.cinema_ticket_booking_app.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home), MovieAdapter.OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var imageSlider: ImageSlider
    private lateinit var bottomSlider: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var list: List<Movie>
    private lateinit var txtNowShowing: TextView
    private lateinit var txtComingSoon: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.bind(view)
        imageSlider = binding.slider
        txtNowShowing = binding.txtNowShowing
        txtComingSoon = binding.txtComingSoon
        bottomSlider = binding.rcvBottomSilder

        slideShow(imageSlider)
        bottomSlideShow()


        txtNowShowing.setTextColor(txtNowShowing.context.getColor(R.color.blueCustom))
        //Mac dinh hien thi On showing
        fetchMovies(true)
        //Su kien click tren Now showing
        txtNowShowing.setOnClickListener{
            fetchMovies(true)
            txtNowShowing.setTextColor(txtNowShowing.context.getColor(R.color.blueCustom))
            txtComingSoon.setTextColor(txtComingSoon.context.getColor(R.color.light_gray))
        }
        //Su kien click tren Comming soon
        txtComingSoon.setOnClickListener{
            fetchMovies(false)
            txtNowShowing.setTextColor(txtNowShowing.context.getColor(R.color.light_gray))
            txtComingSoon.setTextColor(txtComingSoon.context.getColor(R.color.blueCustom))
        }
    }

    //top slider
    private fun slideShow(imageSlider: ImageSlider){
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("https://bom.so/QoKB2w"))
        imageList.add(SlideModel("https://bom.so/N4ujZO"))
        imageList.add(SlideModel("https://bom.so/yH6X9U"))

        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
    }

    //bottom slider
    private fun bottomSlideShow(){
        val bottomImageList = mutableListOf <Cinema>()
        bottomImageList.add(Cinema(-1, "Xem Phim Hay - Ngất Ngây Cùng Bánh Phồng Dế Rec Rec",
                            "","https://bom.so/QoKB2w", ""))
        bottomImageList.add(Cinema(-1, "Xem Phim Hay - Ngất Ngây Cùng Bánh Phồng Dế Rec Rec",
                            "","https://bom.so/N4ujZO", ""))
        bottomImageList.add(Cinema(-1, "Xem Phim Hay - Ngất Ngây Cùng Bánh Phồng Dế Rec Rec",
                            "","https://bom.so/yH6X9U", ""))
        bottomImageList.add(Cinema(-1, "Xem Phim Hay - Ngất Ngây Cùng Bánh Phồng Dế Rec Rec",
                            "","https://bom.so/QoKB2w", ""))
        bottomImageList.add(Cinema(-1, "Xem Phim Hay - Ngất Ngây Cùng Bánh Phồng Dế Rec Rec",
                            "","https://bom.so/N4ujZO", ""))

        val adapter = BottomSliderAdapter(bottomImageList)
        bottomSlider.adapter = adapter
        bottomSlider.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    //Doc phim tu api theo trang thai
    //true: Now showing, false: Comming soon
    private fun fetchMovies(status: Boolean){
        val call = ApiCall.service.getMovies("$status")
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