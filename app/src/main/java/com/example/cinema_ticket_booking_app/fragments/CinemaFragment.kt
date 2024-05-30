package com.example.cinema_ticket_booking_app.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.adapters.CinemaAdapter
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.FragmentCinemaBinding
import com.example.cinema_ticket_booking_app.models.Cinema
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CinemaFragment : Fragment(R.layout.fragment_cinema), CinemaAdapter.OnItemCinemaClickListener{
    private lateinit var binding: FragmentCinemaBinding
    private lateinit var cinemaAdapter: CinemaAdapter
    private lateinit var list: List<Cinema>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCinemaBinding.bind(view)

        fetchCinemas()

    }

    private fun fetchCinemas(){
        val call = ApiCall.service.getCinemas()
        call.enqueue(object : Callback<List<Cinema>> {
            override fun onResponse(call: Call<List<Cinema>>, response: Response<List<Cinema>>) {
                if(response.isSuccessful){
                    val rs = response.body()!!
                    list = rs
                    cinemaAdapter = CinemaAdapter(list, this@CinemaFragment)
                    binding.rcvCinema.adapter = cinemaAdapter
                    binding.rcvCinema.layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.VERTICAL,
                        false)
                }
            }
            override fun onFailure(call: Call<List<Cinema>>, t: Throwable) {
                Log.d("a", t.message.toString())
                Log.d("a", t.cause.toString())
            }
        })
    }

    //mo google map voi dia chi cua rap
    @SuppressLint("QueryPermissionsNeeded")
    override fun onCinemaItemClick(position: Int) {
        val item = list[position]
        val address = item.address
        val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address))
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
//        startActivity(mapIntent)
//            // Kiểm tra xem Google Maps có được cài đặt không
//            packageManager.getApplicationInfo("com.google.android.apps.maps", 0)
            // Google Maps được cài đặt, tiến hành Intent
//            if (mapIntent.resolveActivity(setPacketManager()) != null) {
                startActivity(mapIntent)
//            } else {
//                // Không tìm thấy ứng dụng để xử lý Intent
//                Toast.makeText(requireContext(), "Không thể mở Google Maps.", Toast.LENGTH_SHORT).show()
//            }
//        } catch (e: PackageManager.NameNotFoundException) {
//            // Google Maps chưa được cài đặt
//            Toast.makeText(requireContext(), "Google Maps chưa được cài đặt.", Toast.LENGTH_SHORT).show()
//        }

//        public boolean isGoogleMapsInstalled()
//        {
//            try
//            {
//                ApplicationInfo info = getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0 );
//                return true;
//            }
//            catch(PackageManager.NameNotFoundException e)
//            {
//                return false;
//            }
//        }
    }
}