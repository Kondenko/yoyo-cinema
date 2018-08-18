package com.vladimirkondenko.yoyocinema.data.search.repository

import com.vladimirkondenko.yoyocinema.data.search.serivce.SearchService

class SearchRepository(private val searchService: SearchService) {

    fun searchMovide(query: String, page: Int = 1) =searchService.searchMovies(query, page)

}