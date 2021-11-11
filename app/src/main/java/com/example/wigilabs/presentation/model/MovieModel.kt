package com.example.wigilabs.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    var id: Int,
    var poster_path: String,
    var vote_average: String,
    var title: String,
    var release_date: String,
    var overview: String,
    var backdrop_path: String,
    var popularity: String
) : Parcelable {
}