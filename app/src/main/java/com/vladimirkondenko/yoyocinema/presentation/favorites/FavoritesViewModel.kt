package com.vladimirkondenko.yoyocinema.presentation.favorites

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.vladimirkondenko.yoyocinema.domain.favorites.GetFavorites

class FavoritesViewModel(private val getFavorites: GetFavorites) : ViewModel() {

    private val state = MutableLiveData<FavoritesState>()

    init {
        state.value = FavoritesState.Loading()
    }

    fun state(lifecycleOwner: LifecycleOwner, callback: (FavoritesState) -> Unit) = state.observe(lifecycleOwner, Observer(callback))

    fun getFavorites() {
        getFavorites.execute(
                null,
                onSuccess = { state.postValue(FavoritesState.Success(it)) },
                onError = { state.postValue(FavoritesState.Error(it)) })
    }

}