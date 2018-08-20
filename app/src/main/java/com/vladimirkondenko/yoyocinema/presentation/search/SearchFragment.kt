package com.vladimirkondenko.yoyocinema.presentation.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.widget.RxSearchView
import com.vladimirkondenko.yoyocinema.R
import com.vladimirkondenko.yoyocinema.presentation.common.FilmFragment
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
            // TODO Handle each state
            when(state) {
                is SearchState.Initial -> {
                    filmAdapter.clear()
                }
            }
        }
        vm.searchResults(this) {
            filmAdapter.items = ArrayList(it)
        }
    }

}
