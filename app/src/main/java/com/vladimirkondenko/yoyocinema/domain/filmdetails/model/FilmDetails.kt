package com.vladimirkondenko.yoyocinema.domain.filmdetails.model

data class FilmDetails(
        val id: Int,
        val title: String,
        val overview: String,
        val genres: List<String>,
        val runtime: String,
        val releaseDate: String,
        val voteAverage: Float,
        val voteCount: Int,
        val posterPath: String,
        val backdropPath: String
)