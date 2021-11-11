package com.example.wigilabs.data.network.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wigilabs.domain.response.MovieResponse

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieResponse): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieResponse>)

    @Query("SELECT * FROM TableMovie")
    fun loadListMovieAll(): MutableList<MovieResponse>

    @Query("DELETE FROM TableMovie")
    fun deleteAll()
}