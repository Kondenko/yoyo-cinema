package com.vladimirkondenko.yoyocinema.presentation.search

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.vladimirkondenko.yoyocinema.domain.search.model.Film
import com.vladimirkondenko.yoyocinema.domain.search.usecase.SearchMovie

class SearchViewModel(private val searchMovie: SearchMovie) : ViewModel() {

    private val state = MutableLiveData<SearchState>()

    private val searchResults = MutableLiveData<List<Film>>()

    init {
        state.value = SearchState.Initial()
    }

    fun state(lifecycleOwner: LifecycleOwner, callback: (SearchState) -> Unit) = state.observe(lifecycleOwner, Observer(callback))

    fun searchResults(lifecycleOwner: LifecycleOwner, callback: (List<Film>) -> Unit) = searchResults.observe(lifecycleOwner, Observer(callback))

    fun onSearchCleared() {
        state.value = SearchState.Initial()
    }

    fun search(query: String) {
        // TODO Use pagination
        val page = 1
        state.postValue(SearchState.Loading())
        searchMovie.execute(
                SearchMovie.Params(query, page),
                onSuccess = {
                    state.postValue(SearchState.Success(page))
                    searchResults.value = it
                },
                onError = { t -> state.value = SearchState.Error(page, t) }
        )
    }

    override fun onCleared() {
        searchMovie.dispose()
        super.onCleared()
    }

}