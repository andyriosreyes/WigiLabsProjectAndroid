package com.example.wigilabs.data.network

import com.example.wigilabs.util.Failure
import com.example.wigilabs.data.network.remote.RetrofitApiClient
import com.example.wigilabs.domain.response.MovieResponse
import com.example.wigilabs.util.ResultType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.wigilabs.data.mapper.MovieMapper.toDomain
import com.example.wigilabs.domain.response.MainMovieResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class Service @Inject constructor(private val api: RetrofitApiClient) {

    suspend fun getMovies(
        api_key: String,
        language: String
    ): ResultType<MainMovieResponse?, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllMovies(api_key, language)
                if (response.isSuccessful) {
//                    val y = response.body()?.results
//                    ResultType.Success(y?.map { it.toDomain() } ?: emptyList())
                    ResultType.Success(response.body()?.toDomain())
                } else {
                    ResultType.Error(Failure.UnExpected)
                }
            } catch (t: Exception) {
                ResultType.Error(mapToDomainException(t))
            }
        }
    }

    private fun mapToDomainException(remoteException: Exception): Failure {
        return when (remoteException) {
            is IOException -> Failure.NetworkConnection
            is HttpException -> Failure.Http
            else -> Failure.UnExpected
        }
    }
}