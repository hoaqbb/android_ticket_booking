package com.example.cinema_ticket_booking_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.NonNull
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.api.ApiCall
import com.example.cinema_ticket_booking_app.databinding.ActivityDetailMovieBinding
import com.example.cinema_ticket_booking_app.fragments.InfoMovieFragment
import com.example.cinema_ticket_booking_app.fragments.ShowtimesMovieFragment
import com.example.cinema_ticket_booking_app.models.Movie
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movieId = intent.getIntExtra("movie_id", -1)

        val infoFragment = InfoMovieFragment()
        val showtimesFragment = ShowtimesMovieFragment()

        getMovieById(movieId)

        //hien thi fragment info ban dau
        changeFragment(infoFragment, movieId)

        //thay doi fragment khi click Info
        binding.txtInfo.setOnClickListener{
            changeFragment(infoFragment, movieId)
        }
        //thay doi fragment khi click Showtimes
        binding.txtShowtimes.setOnClickListener {
            changeFragment(showtimesFragment, movieId)
        }

      //this is video
        val youTubePlayerView = binding.youtubePlayerView
        var youTubePlayer: YouTubePlayer? = null

        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                val videoId = "Mm-_TKuK15A"
                youTubePlayer.cueVideo(videoId, 0f)
                youTubePlayer.pause()
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                if (state == PlayerConstants.PlayerState.PLAYING) {
                    val intent =
                        Intent(this@DetailMovieActivity, FullScreenActivity::class.java)
                    startActivity(intent)
                }
            }
        })

    }


    private fun getMovieById(movie_id: Int){
        val call = ApiCall.service.getMovieById(movie_id)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if(response.isSuccessful){
                    val movie = response.body()!! //luu y tra ve doi tuong chu ko phai mang doi tuong
                    binding.txtMovieName.text = movie.movie_name
                    Picasso.get().load(movie.movie_img).into(binding.imgMovie)
                    binding.txtDuration.text = movie.duration.toString()
                    binding.txtReleaseDate.text = movie.release_date

                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("a", t.message.toString())
                Log.d("a", t.cause.toString())
            }
        })
    }

    //thay doi noi dung activity
    private fun changeFragment(fragment: Fragment, movieId: Int){
        val bundle = Bundle()
        bundle.putInt("id", movieId)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
    }
}
