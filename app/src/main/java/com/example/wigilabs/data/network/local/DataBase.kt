package com.example.wigilabs.data.network.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wigilabs.domain.response.MovieResponse

@Database(entities = [MovieResponse::class], version = 1, exportSchema = false)
abstract class DataBase()  : RoomDatabase() {
    abstract val movieDAO: MovieDAO

    companion object {
        const val DB_NAME = "Wigi.db"
    }
}
