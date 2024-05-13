package com.example.cinema_ticket_booking_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.databinding.ActivityMainBinding
import com.example.cinema_ticket_booking_app.fragments.AccountFragment
import com.example.cinema_ticket_booking_app.fragments.HomeFragment
import com.example.cinema_ticket_booking_app.fragments.CinemaFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeFragment = HomeFragment()
        val cinemaFragment = CinemaFragment()
        val accountFragment = AccountFragment()

        makeCurrentFragment(homeFragment)

        binding.bottomNavBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> makeCurrentFragment(homeFragment)
                R.id.cinema -> makeCurrentFragment(cinemaFragment)
                R.id.account -> makeCurrentFragment(accountFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
}