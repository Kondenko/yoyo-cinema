package com.vladimirkondenko.yoyocinema.presentation.search

sealed class SearchState(val page: Int) {
    class Initial(page: Int = 0) : SearchState(page)
    class Success(page: Int) : SearchState(page)
    class Loading(page: Int = 0) : SearchState(page)
    class Error(page: Int, val exception: Throwable) : SearchState(page)
    class Empty(page: Int = 0) : SearchState(page)
}