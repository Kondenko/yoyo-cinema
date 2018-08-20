package com.vladimirkondenko.yoyocinema.presentation.search

import com.vladimirkondenko.yoyocinema.domain.search.model.Film

sealed class SearchState(val page: Int) {
    class Initial(page: Int = 0) : SearchState(page)
    class Success(page: Int, val model: List<Film>) : SearchState(page)
    class Loading(page: Int = 0) : SearchState(page)
    class Error(page: Int, val exception: Throwable) : SearchState(page)
    class Empty(page: Int = 0) : SearchState(page)
}