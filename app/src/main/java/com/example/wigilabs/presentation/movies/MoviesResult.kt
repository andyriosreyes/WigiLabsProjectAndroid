package com.example.wigilabs.presentation.movies

import com.example.wigilabs.presentation.model.MovieModel
import com.example.wigilabs.util.Failure

sealed class MoviesResult {

    sealed class ListMoviesResult : MoviesResult() {

        object Loading : ListMoviesResult()

        class Success(val movies: List<MovieModel>) : ListMoviesResult()

        class Error(val failure: Failure) : ListMoviesResult()

    }
}
