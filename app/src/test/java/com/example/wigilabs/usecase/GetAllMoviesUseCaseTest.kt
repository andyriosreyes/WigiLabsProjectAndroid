package com.example.wigilabs.usecase

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
class GetAllMoviesUseCaseTest {

    @Rule
    @JvmField
    val initRule : MockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    val instantTaskExecutor = InstantTaskExecutorRule()

    private var getAllMoviesUseCaseTest : GetAllMoviesUseCase ?= null
    private var movieResponse: MainMovieResponse ?= null
    private lateinit var failure: Failure

    private val apikey: String = "09963e300150f9831c46a1828a82a984"
    private val lenguage : String = "en-US"


    @Before
    fun setUp(){
        getAllMoviesUseCaseTest = Mockito.mock(GetAllMoviesUseCase::class.java)
        movieResponse = Mockito.mock(MainMovieResponse::class.java)
        failure = Mockito.mock(Failure::class.java)
    }

    private fun generateResultListMoviesSuccess(model: MainMovieResponse): ResultType<MainMovieResponse?, Failure> {
        return ResultType.Success(model)
    }

    private fun generateResultListMoviesFailure(failure: Failure): ResultType<MainMovieResponse?, Failure> {
        return ResultType.Error(failure)
    }

    @Test
    fun getListMoviesError()  = runBlocking{
        BDDMockito.given(getAllMoviesUseCaseTest?.invoke(apikey,lenguage)).willReturn(
            generateResultListMoviesFailure(failure)
        )
        val result = getAllMoviesUseCaseTest?.invoke(apikey,lenguage)
        Mockito.verify(getAllMoviesUseCaseTest)?.invoke(apikey,lenguage)
        if (result is ResultType.Error) {
            assertEquals(failure, result.value)
        }
    }

    @Test
    fun getListMoviesSuccess()  = runBlocking{
        BDDMockito.given(getAllMoviesUseCaseTest?.invoke(apikey,lenguage)).willReturn(
            movieResponse?.let { generateResultListMoviesSuccess(it) }
        )
        val result = getAllMoviesUseCaseTest?.invoke(apikey,lenguage)
        Mockito.verify(getAllMoviesUseCaseTest)?.invoke(apikey,lenguage)
        if (result is ResultType.Success) {
            assertNotNull(result.value)
        }
    }
}