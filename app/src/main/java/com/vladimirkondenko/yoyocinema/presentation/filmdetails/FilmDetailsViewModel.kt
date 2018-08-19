package com.vladimirkondenko.yoyocinema.presentation.filmdetails

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.vladimirkondenko.yoyocinema.domain.filmdetails.usecase.GetDetails
import com.vladimirkondenko.yoyocinema.presentation.main.FilmDetailsState

class FilmDetailsViewModel(private val getDetails: GetDetails) : ViewModel() {

    private val state = MutableLiveData<FilmDetailsState>()

    fun observeState(lifecycleOwner: LifecycleOwner, callback: (FilmDetailsState) -> Unit) = state.observe(lifecycleOwner, Observer(callback))

    init {
        state.value = FilmDetailsState.Loading()
    }

    fun getDetails(id: Int) {
        getDetails.execute(
                id,
                onSuccess = { state.postValue(FilmDetailsState.Success(it)) },
                onError = { state.postValue(FilmDetailsState.Error(it)) }
        )
    }

    fun onDetailsNotFound() {
        state.value = FilmDetailsState.Error(NullPointerException("Arguments not found"))
    }

}