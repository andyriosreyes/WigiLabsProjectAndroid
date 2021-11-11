package com.example.wigilabs.data.repository

import com.example.wigilabs.util.Failure
import com.example.wigilabs.data.network.Service
import com.example.wigilabs.data.network.local.DataBase
import com.example.wigilabs.domain.response.MainMovieResponse
import com.example.wigilabs.domain.response.MovieResponse
import com.example.wigilabs.util.ResultType
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val api: Service,
    private val database: DataBase
) {
    suspend fun getAllMoviesRepository(api_key: String, lenguage: String): ResultType<MainMovieResponse?, Failure> {
        return api.getMovies(api_key, lenguage)
    }

    fun addMoviesDAO(articles: List<MovieResponse>) {
        database.movieDAO.insertAll(articles)
    }

    fun getAllMoviesDAO(): List<MovieResponse> {
        return database.movieDAO.loadListMovieAll()
    }

    fun deleteAllMoviesDAO(){
        return database.movieDAO.deleteAll()
    }
}