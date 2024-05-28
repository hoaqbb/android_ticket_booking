package com.example.cinema_ticket_booking_app.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cinema_ticket_booking_app.R
import com.example.cinema_ticket_booking_app.databinding.FragmentAccountBinding
import com.example.cinema_ticket_booking_app.session.UserSession


class AccountFragment : Fragment(R.layout.fragment_account) {
    private lateinit var binding: FragmentAccountBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAccountBinding.bind(view)

        //kiem tra user da dang nhap chua
        //true: su dung ProfileUserFragment
        if(UserSession.loadUserSession(requireContext()).user_id!!>0){
            binding.btnLogout.visibility = View.VISIBLE;
            makeCurrentFragment(ProfileUserFragment())
        } else{
            //false: su dung LoginFragment
            makeCurrentFragment(LoginFragment())
        }

        binding.btnLogout.setOnClickListener {
            UserSession.clearUserSession(requireContext())
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        childFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commitNow()
        }
}