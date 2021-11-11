package com.example.wigilabs.data.network.remote

import com.example.wigilabs.data.model.MainMovieEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiClient {
    @GET("popular")
    suspend fun getAllMovies(
        @Query("api_key") api_key: String,
        @Query("language") page: String): Response<MainMovieEntity>

}