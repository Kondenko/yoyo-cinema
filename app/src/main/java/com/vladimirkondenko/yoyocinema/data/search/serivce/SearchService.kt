package com.vladimirkondenko.yoyocinema.data.search.serivce

import com.vladimirkondenko.yoyocinema.data.search.model.SearchResultModel
import com.vladimirkondenko.yoyocinema.utils.apiKey
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

private const val queryParam = "query"
private const val pageParam = "page"

interface SearchService {

    @GET("search/movie?api_key=$apiKey")
    fun searchFilms(@Query(queryParam) query: String, @Query(pageParam) page: Int): Single<SearchResultModel>

}