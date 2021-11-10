package com.example.wigilabs.domain.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "TableMovie")
@Parcelize
data class MovieResponse(
    @PrimaryKey var id: Int,
    var poster_path: String,
    var vote_average: String,
    var title: String,
    var release_date: String,
    var overview: String,
    var backdrop_path: String,
    var popularity : String
):Parcelable {
}