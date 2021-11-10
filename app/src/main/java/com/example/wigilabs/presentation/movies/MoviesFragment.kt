package com.example.wigilabs.presentation.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.wigilabs.databinding.FragmentMoviesBinding
import com.example.wigilabs.presentation.model.MovieModel
import com.example.wigilabs.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(), OnMoviesAdapterListener {
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    private var adapter: MoviesAdapter? = null
    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel.allMoviesExecute(Constants.API_KEY,Constants.LENGUAGE)
        adapter = MoviesAdapter(this,context)
        fragmentMoviesBinding.movieRV.adapter = adapter
        observerGetMovies()
        observerGetMoviesCache()
    }

    private fun observerGetMovies(){
        moviesViewModel.movieLiveData.observe(viewLifecycleOwner,{
            when (it){
                is MoviesResult.ListMoviesResult.Loading -> {

                }
                is MoviesResult.ListMoviesResult.Success -> {
                    initRecyclerView(it.movies)
                }
                is MoviesResult.ListMoviesResult.Error -> {
                    moviesViewModel.allMoviesCache()
                }
            }
        })
    }

    private fun observerGetMoviesCache(){
        moviesViewModel._movieLiveData2.observe(viewLifecycleOwner,{
            when (it){
                is MoviesResult.ListMoviesResult.Loading -> {
                    Toast.makeText(requireContext(),"Sin Conexion..",Toast.LENGTH_SHORT).show()
                }
                is MoviesResult.ListMoviesResult.Success -> {
                    initRecyclerView(it.movies)
                }
                is MoviesResult.ListMoviesResult.Error -> {
                    Toast.makeText(requireContext(),"Ocurrio un Error",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initRecyclerView(articles: List<MovieModel>) {
        adapter?.addData(articles.sortedBy { it.id })
    }

    override fun onSelectMovie(movieResponse: MovieModel) {
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieSelectFragment(movieResponse)
        findNavController().navigate(action)
    }
}