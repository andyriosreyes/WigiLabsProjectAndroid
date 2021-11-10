package com.example.wigilabs.presentation.mapper

import com.example.wigilabs.domain.response.MovieResponse
import com.example.wigilabs.presentation.model.MovieModel

object MovieModelMapper {

    fun MovieResponse.toModel() = MovieModel(
        id = id,
        poster_path = poster_path,
        vote_average = vote_average,
        title = title,
        release_date = release_date,
        overview = overview,
        backdrop_path = backdrop_path,
        popularity = popularity
    )
}