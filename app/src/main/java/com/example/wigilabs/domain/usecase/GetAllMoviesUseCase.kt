package com.example.wigilabs.domain.usecase

import com.example.wigilabs.util.Failure
import com.example.wigilabs.data.repository.MovieRepositoryImp
import com.example.wigilabs.domain.response.MovieResponse
import com.example.wigilabs.util.ResultType
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(private val repository: MovieRepositoryImp) {

    suspend operator fun invoke(
        api_key: String,
        lenguage: String
    ): ResultType<List<MovieResponse>, Failure> =
        repository.getAllMoviesRepository(api_key, lenguage)

    fun addMoviesDAO(movies: List<MovieResponse>) {
        repository.addMoviesDAO(movies)
    }

    fun allMoviesDAO(): List<MovieResponse> {
        return repository.getAllMoviesDAO()
    }

    fun deleteMoviesDAO(){
        return repository.deleteAllMoviesDAO()
    }
}
