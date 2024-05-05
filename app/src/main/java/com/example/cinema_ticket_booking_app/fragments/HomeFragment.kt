package com.example.cinema_ticket_booking_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.adapters.MovieAdapter
import com.example.cinema_ticket_booking_app.databinding.FragmentHomeBinding
import com.example.cinema_ticket_booking_app.models.Movie

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var imageSlider: ImageSlider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.bind(view)
        imageSlider = binding.slider

        slideShow(imageSlider)

        val list = mutableListOf<Movie>()
        list.add(Movie(R.drawable.a, "Dune"))
        list.add(Movie(R.drawable.b, "Han"))
        list.add(Movie(R.drawable.c, "Teeth"))
        list.add(Movie(R.drawable.d, "No way up!"))
        list.add(Movie(R.drawable.a, "Dune"))
        list.add(Movie(R.drawable.b, "Han"))
        list.add(Movie(R.drawable.c, "Teeth"))
        list.add(Movie(R.drawable.d, "No way up!"))


        val adapter = MovieAdapter(list)

        binding.rcvMovie.adapter = adapter
        binding.rcvMovie.layoutManager = GridLayoutManager(
            context,
            2,
            GridLayoutManager.VERTICAL,
            false)
    }

    private fun slideShow(imageSlider: ImageSlider){
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("https://bom.so/QoKB2w"))
        imageList.add(SlideModel("https://bom.so/N4ujZO"))
        imageList.add(SlideModel("https://bom.so/yH6X9U"))

        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
    }
}