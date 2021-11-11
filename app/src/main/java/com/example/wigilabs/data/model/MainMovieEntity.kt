package com.example.wigilabs.data.model

import com.google.gson.annotations.SerializedName

data class MainMovieEntity(
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("results") val results: List<MovieEntity>) {
}