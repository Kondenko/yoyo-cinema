package com.vladimirkondenko.yoyocinema.presentation.filmdetails

import com.vladimirkondenko.yoyocinema.domain.films.model.FilmDetails

sealed class FilmDetailsState {
    class Success(val film: FilmDetails, val isFavorite: Boolean) : FilmDetailsState()
    class Loading : FilmDetailsState()
    open class Error(val exception: Throwable) : FilmDetailsState()
    class FavoriteError(exception: Throwable) : Error(exception)
}