package com.krayapp.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieInfo(
    val title: String?,
    val description: String?,
    val rate: Float?,
    val imagePath:String?
):Parcelable
