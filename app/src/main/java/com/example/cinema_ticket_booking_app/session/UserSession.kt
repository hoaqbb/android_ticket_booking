package com.example.cinema_ticket_booking_app.session

import android.content.Context
import com.example.cinema_ticket_booking_app.models.User

object UserSession {

    fun saveUserSession(context: Context, userId: Int) {
        val sharedPref = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("user_id", userId)
            apply()
        }
    }

    fun loadUserSession(context: Context): User {
        val sharedPref = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val userId = sharedPref.getInt("user_id", -1)
        return User(
            userId,
            null, null, null, null, null
        )
    }

    fun clearUserSession(context: Context) {
        val sharedPref = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            remove("user_id")
            apply()
        }
    }
}