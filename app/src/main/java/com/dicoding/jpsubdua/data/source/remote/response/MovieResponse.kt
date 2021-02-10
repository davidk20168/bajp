package com.dicoding.jpsubdua.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    val movieId: String,
    val title: String,
    val category : String,
    val synopsis: String,
    val imagePoster: String
) : Parcelable
