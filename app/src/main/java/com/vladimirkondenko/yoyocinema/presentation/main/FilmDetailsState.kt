package com.vladimirkondenko.yoyocinema.presentation.main

import com.vladimirkondenko.yoyocinema.domain.films.model.FilmDetails

sealed class FilmDetailsState {
    class Success(val model: FilmDetails) : FilmDetailsState()
    class Loading : FilmDetailsState()
    class Error(val exception: Throwable) : FilmDetailsState()
}