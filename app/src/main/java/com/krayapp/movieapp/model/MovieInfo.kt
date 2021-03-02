package com.krayapp.movieapp.model

import android.os.Parcel
import android.os.Parcelable

data class MovieInfo(
    val title: String? = "First Movie",
    val description: String? = "It will be the best app",
    val rate: Int = 5
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(rate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieInfo> {
        override fun createFromParcel(parcel: Parcel): MovieInfo {
            return MovieInfo(parcel)
        }

        override fun newArray(size: Int): Array<MovieInfo?> {
            return arrayOfNulls(size)
        }
    }
}


