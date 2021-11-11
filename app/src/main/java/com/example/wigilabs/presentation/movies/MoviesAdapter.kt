package com.example.wigilabs.presentation.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wigilabs.databinding.ItemMovieBinding
import com.example.wigilabs.presentation.model.MovieModel
import com.example.wigilabs.util.Constants
import java.util.*

class MoviesAdapter(val onMoviesAdapterListener: OnMoviesAdapterListener, val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listMovie: MutableList<MovieModel> = ArrayList()
    private val contextMain : Context ?= context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            // Inflate the layout for this fragment
            val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (contextMain != null) {
            (holder as MovieViewHolder).onBind(contextMain,getItem(position),)
        }
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    private fun getItem(position: Int): MovieModel {
        return listMovie[position]
    }

    fun addData(list: List<MovieModel>) {
        this.listMovie.addAll(list)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val itemBinding: ItemMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(context : Context,movieResponse: MovieModel) {
            val ruta = Constants.BASE_IMG_URL + movieResponse.poster_path
            Glide.with(context)
                .load(ruta)
                .into(itemBinding.ivMovie)

            itemBinding.tvMoviesTitle.text = movieResponse.title
            itemBinding.tvMoviesDescription.text = movieResponse.overview
            itemBinding.itemMovieCL.setOnClickListener {
                onMoviesAdapterListener.onSelectMovie(movieResponse)
            }
        }
    }

}