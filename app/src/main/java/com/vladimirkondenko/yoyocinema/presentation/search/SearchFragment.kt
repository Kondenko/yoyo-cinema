package com.vladimirkondenko.yoyocinema.presentation.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.jakewharton.rxbinding2.widget.RxSearchView
import com.vladimirkondenko.yoyocinema.R
import com.vladimirkondenko.yoyocinema.presentation.common.FilmFragment
import com.vladimirkondenko.yoyocinema.utils.showErrorSnackbar
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class SearchFragment : FilmFragment() {

    private val vm: SearchViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(search_recyclerview_movies)
        viewDisposables += RxSearchView.queryTextChanges(searchView)
                .subscribeOn(uiScheduler)
                .observeOn(uiScheduler)
                .doOnNext { if (it.isEmpty()) vm.onSearchCleared() }
                .filter { it.length > 2 }
                .debounce(250, TimeUnit.MILLISECONDS)
                .map { it.toString() }
                .subscribe(vm::search)
        vm.state(this) { state ->
            when (state) {
                is SearchState.Initial -> {
                    search_include_initial_state.isVisible = true
                    search_progressbar.isVisible = false
                    search_include_empty_state.isVisible = false
                    filmAdapter.clear()
                }
                is SearchState.Success -> {
                    filmAdapter.items = ArrayList(state.model)
                    search_progressbar.isVisible = false
                    search_include_initial_state.isVisible = false
                    search_include_empty_state.isVisible = false
                }
                is SearchState.Loading -> {
                    search_progressbar.isVisible = true
                    search_include_empty_state.isVisible = false
                    search_include_initial_state.isVisible = false
                }
                is SearchState.Empty -> {
                    search_include_empty_state.isVisible = true
                    search_include_initial_state.isVisible = false
                    search_progressbar.isVisible = false
                }
                is SearchState.Error -> {
                    view.showErrorSnackbar(R.string.search_error_message) { vm.retrySearch() }
                    search_include_empty_state.isVisible = false
                    search_include_initial_state.isVisible = false
                    search_progressbar.isVisible = false
                }
            }
        }
    }

}