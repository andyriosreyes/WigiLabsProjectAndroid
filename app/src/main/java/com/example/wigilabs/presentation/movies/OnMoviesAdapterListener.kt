package com.example.wigilabs.presentation.movies

import com.example.wigilabs.presentation.model.MovieModel

interface OnMoviesAdapterListener {
    fun onSelectMovie(movieResponse: MovieModel)
}