package com.example.wigilabs.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.wigilabs.domain.response.MainMovieResponse
import com.example.wigilabs.domain.usecase.GetAllMoviesUseCase
import com.example.wigilabs.util.Failure
import com.example.wigilabs.util.ResultType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImpTest{
    @Rule
    @JvmField
    val initRule : MockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    val instantTaskExecutor = InstantTaskExecutorRule()

    private var repositoryImpTest : MovieRepositoryImp?= null
    private var movieResponse: MainMovieResponse?= null
    private lateinit var failure: Failure

    private val apikey: String = "09963e300150f9831c46a1828a82a984"
    private val lenguage : String = "en-US"

    @Before
    fun setUp(){
        repositoryImpTest = Mockito.mock(MovieRepositoryImp::class.java)
        movieResponse = Mockito.mock(MainMovieResponse::class.java)
        failure = Mockito.mock(Failure::class.java)
    }

    private fun generateRepositorySuccess(model: MainMovieResponse): ResultType<MainMovieResponse?, Failure> {
        return ResultType.Success(model)
    }

    private fun generateRepositoryFailure(failure: Failure): ResultType<MainMovieResponse?, Failure> {
        return ResultType.Error(failure)
    }


    @Test
    fun repositorySuccess()  = runBlocking{
        BDDMockito.given(repositoryImpTest?.getAllMoviesRepository(apikey,lenguage)).willReturn(
            movieResponse?.let { generateRepositorySuccess(it) }
        )
        val result = repositoryImpTest?.getAllMoviesRepository(apikey,lenguage)
        Mockito.verify(repositoryImpTest)?.getAllMoviesRepository(apikey,lenguage)
        if (result is ResultType.Success) {
            assertNotNull(result.value)
        }
    }

    @Test
    fun repositoryError()  = runBlocking{
        BDDMockito.given(repositoryImpTest?.getAllMoviesRepository(apikey,lenguage)).willReturn(
            generateRepositoryFailure(failure)
        )
        val result = repositoryImpTest?.getAllMoviesRepository(apikey,lenguage)
        Mockito.verify(repositoryImpTest)?.getAllMoviesRepository(apikey,lenguage)
        if (result is ResultType.Error) {
            assertEquals(failure,result.value)
        }
    }

}