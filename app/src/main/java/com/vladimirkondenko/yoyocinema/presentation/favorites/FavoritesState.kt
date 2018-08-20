package com.vladimirkondenko.yoyocinema.presentation.favorites

import com.vladimirkondenko.yoyocinema.domain.search.model.Film

sealed class FavoritesState {
    class Success(val model: List<Film>) : FavoritesState()
    class Loading() : FavoritesState()
    class Error(val exception: Throwable) : FavoritesState()
    class Empty() : FavoritesState()
}