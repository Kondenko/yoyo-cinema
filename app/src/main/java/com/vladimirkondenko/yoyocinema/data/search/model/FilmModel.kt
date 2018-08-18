package com.vladimirkondenko.yoyocinema.data.search.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FilmModel {
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int? = null
    @SerializedName("id")
    @Expose
    val id: Int? = null
    @SerializedName("video")
    @Expose
    val video: Boolean? = null
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Float? = null
    @SerializedName("title")
    @Expose
    val title: String? = null
    @SerializedName("popularity")
    @Expose
    val popularity: Float? = null
    @SerializedName("poster_path")
    @Expose
    val posterPath: Any? = null
    @SerializedName("original_language")
    @Expose
    val originalLanguage: String? = null
    @SerializedName("original_title")
    @Expose
    val originalTitle: String? = null
    @SerializedName("genre_ids")
    @Expose
    val genreIds: List<Int>? = null
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: Any? = null
    @SerializedName("adult")
    @Expose
    val adult: Boolean? = null
    @SerializedName("overview")
    @Expose
    val overview: String? = null
    @SerializedName("release_date")
    @Expose
    val releaseDate: String? = null
}