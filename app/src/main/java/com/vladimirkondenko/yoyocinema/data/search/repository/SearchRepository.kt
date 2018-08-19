package com.vladimirkondenko.yoyocinema.data.search.repository

import com.vladimirkondenko.yoyocinema.data.search.serivce.SearchService

class SearchRepository(private val searchService: SearchService) {

    fun searchMovie(query: String, page: Int = 1) = searchService.searchFilms(query, page)

}