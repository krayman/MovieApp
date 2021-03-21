package com.krayapp.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieInfo(
    val title: String? = "First Movie",
    val description: String? = "It will be the best app",
    val rate: Float? = 5.0f
):Parcelable
