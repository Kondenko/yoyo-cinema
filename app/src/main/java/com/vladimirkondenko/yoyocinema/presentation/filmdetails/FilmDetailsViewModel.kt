package com.vladimirkondenko.yoyocinema.presentation.filmdetails

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.vladimirkondenko.yoyocinema.domain.favorites.SetFavorite
import com.vladimirkondenko.yoyocinema.domain.films.usecase.GetDetails
import com.vladimirkondenko.yoyocinema.presentation.filmdetails.FilmDetailsActivity.Companion.invalidId

class FilmDetailsViewModel(private val getDetails: GetDetails, private val setFavorite: SetFavorite) : ViewModel() {

    private val state = MutableLiveData<FilmDetailsState>()

    fun state(lifecycleOwner: LifecycleOwner, callback: (FilmDetailsState) -> Unit) = state.observe(lifecycleOwner, Observer(callback))

    init {
        state.value = FilmDetailsState.Loading()
    }

    /**
     * Mark a film as favorite if it has been loaded successfully before.
     */
    fun setFavorite(favorite: Boolean) {
        state.value.takeIf { it is FilmDetailsState.Success }?.let {
            setFavorite.execute(
                    SetFavorite.Params((it as FilmDetailsState.Success).film, favorite),
                    onError = { state.postValue(FilmDetailsState.FavoriteError(it)) }
            )
        }
    }

    fun getDetails(id: Int) {
        if (id != invalidId) {
            getDetails.execute(
                    id,
                    onSuccess = { (film, isFavorite) -> state.postValue(FilmDetailsState.Success(film, isFavorite)) },
                    onError = { state.postValue(FilmDetailsState.Error(it)) }
            )
        } else {
            state.postValue(FilmDetailsState.Error(IllegalArgumentException("Illegal film id")))
        }
    }

    fun onDetailsNotFound() {
        state.value = FilmDetailsState.Error(NullPointerException("Arguments not found"))
    }

}