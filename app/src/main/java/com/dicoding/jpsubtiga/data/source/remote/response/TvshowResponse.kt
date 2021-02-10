package com.dicoding.jpsubtiga.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvshowResponse(
    val tvshowId: String,
    val title: String,
    val category : String,
    val synopsis: String,
    val imagePoster: String
) : Parcelable
