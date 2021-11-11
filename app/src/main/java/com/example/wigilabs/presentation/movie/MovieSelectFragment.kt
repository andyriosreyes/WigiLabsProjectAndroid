package com.example.wigilabs.presentation.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.wigilabs.databinding.FragmentMovieSelectBinding
import com.example.wigilabs.presentation.model.MovieModel
import com.example.wigilabs.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieSelectFragment : Fragment() {
    private lateinit var binding: FragmentMovieSelectBinding
    private val args : MovieSelectFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieSelectBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieResponse = args.movieResponse
        setUpMovie(movieResponse)

    }

    private fun setUpMovie(movieResponse: MovieModel){
        val ruta = Constants.BASE_IMG_URL + movieResponse.backdrop_path
        Glide.with(requireContext())
            .load(ruta)
            .into(binding.ivMovieMain)

        binding.tvMovieTitle.text = movieResponse.title
        binding.tvMovieDate.text = movieResponse.release_date
        binding.tvMovieVotation.text = movieResponse.vote_average
        binding.tvMovieDescription.text = movieResponse.overview
        binding.tvMoviePopularity.text = movieResponse.popularity
    }
}