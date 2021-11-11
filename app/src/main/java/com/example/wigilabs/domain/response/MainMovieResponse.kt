package com.example.wigilabs.domain.response

import java.io.Serializable

data class MainMovieResponse(
    var total_pages: Int,
    var results: List<MovieResponse>): Serializable {
}