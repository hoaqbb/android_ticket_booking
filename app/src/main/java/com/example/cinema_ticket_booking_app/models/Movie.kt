package com.example.cinema_ticket_booking_app.models

import android.os.Parcel
import android.os.Parcelable
import java.util.Date

data class Movie(
    var movie_id: Int,
    var movie_name: String,
    var status: Byte,
    var movie_img: String,
    var duration: Int,
    var actors: String,
    var directors: String,
    var release_date: String,
    var description: String
    )
//    : Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readInt(),
//        parcel.readString() ?: "",
//        parcel.readByte() != 0.toByte(),
//        parcel.readString() ?: "",
//        parcel.readInt(),
//        parcel.readString() ?: "",
//        parcel.readString() ?: "",
//        release_date = Date(parcel.readLong()),
//        parcel.readString() ?: ""
//    )
//
//    override fun describeContents(): Int {
//        return  0
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeInt(movie_id)
//        parcel.writeString(movie_name)
//        parcel.writeByte(if (status) 1 else 0)
//        parcel.writeString(movie_img)
//        parcel.writeInt(duration)
//        parcel.writeString(actors)
//        parcel.writeString(directors)
//        parcel.writeLong(release_date.time)
//        parcel.writeString(description)
//    }
//
//    companion object CREATOR : Parcelable.Creator<Movie> {
//        override fun createFromParcel(parcel: Parcel): Movie {
//            return Movie(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Movie?> {
//            return arrayOfNulls(size)
//        }
//    }
//}