package com.example.wigilabs.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wigilabs.domain.usecase.GetAllMoviesUseCase
import com.example.wigilabs.presentation.mapper.MovieModelMapper.toModel
import com.example.wigilabs.util.Failure
import com.example.wigilabs.util.ResultType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesUseCase
) : ViewModel() {

    private val _movieLiveData = MutableLiveData<MoviesResult.ListMoviesResult>()
    val movieLiveData: LiveData<MoviesResult.ListMoviesResult> get() = _movieLiveData
    val _movieLiveData2 = MutableLiveData<MoviesResult.ListMoviesResult>()

    fun allMoviesExecute(api_key : String, lenguage : String) {
        viewModelScope.launch {
            _movieLiveData.value = MoviesResult.ListMoviesResult.Loading
            val result = withContext(Dispatchers.IO) {
                getAllMoviesUseCase(api_key,lenguage)
            }
            when (result) {
                is ResultType.Success -> {
                    val movies = result.value.map { it.toModel() }
                    getAllMoviesUseCase.deleteMoviesDAO()

                    if(getAllMoviesUseCase.allMoviesDAO().isEmpty()){
                        getAllMoviesUseCase.addMoviesDAO(result.value)
                    }
                    _movieLiveData.value = MoviesResult.ListMoviesResult.Success(movies)
                }
                is ResultType.Error -> {
                    when (result.value){
                        is Failure.NetworkConnection,Failure.Http,Failure.UnExpected -> {
                            _movieLiveData.value = MoviesResult.ListMoviesResult.Error(result.value)
                        }
                    }
                }
            }
        }
    }

    fun allMoviesCache() {
        _movieLiveData.value = MoviesResult.ListMoviesResult.Loading
        val result = getAllMoviesUseCase.allMoviesDAO()
            if (result.isNotEmpty()) {
                _movieLiveData.value = MoviesResult.ListMoviesResult.Success(result.map { it.toModel() })
            }else{
                _movieLiveData.value = MoviesResult.ListMoviesResult.Error(Failure.UnExpected)
            }
        }
    }