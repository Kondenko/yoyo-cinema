package com.vladimirkondenko.yoyocinema.data.favorites.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.vladimirkondenko.yoyocinema.data.Converters

@Entity(tableName = "favorites")
data class FilmDetailsModel(
        @PrimaryKey
        val id: Int,
        val title: String,
        val overview: String,
        @TypeConverters(Converters::class)
        val genres: ListWrapper?,
        val runtime: String,
        val releaseDate: String,
        val voteAverage: Float,
        val voteCount: Int,
        val posterPath: String,
        val backdropPath: String
)