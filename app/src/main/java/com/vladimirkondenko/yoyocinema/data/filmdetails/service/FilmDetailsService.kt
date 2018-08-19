package com.vladimirkondenko.yoyocinema.data.filmdetails.service

import com.vladimirkondenko.yoyocinema.data.filmdetails.model.FilmDetailsModel
import com.vladimirkondenko.yoyocinema.utils.apiKey
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

const val pathId = "id"

interface FilmDetailsService {

    @GET("movie/{$pathId}?api_key=$apiKey")
    fun getDetails(@Path(pathId) id: Int): Single<FilmDetailsModel>

}