package com.example.wigilabs.data.mapper

import com.example.wigilabs.data.model.MovieEntity
import com.example.wigilabs.domain.response.MovieResponse

object MovieMapper {

    fun MovieEntity.toDomain() = MovieResponse(
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