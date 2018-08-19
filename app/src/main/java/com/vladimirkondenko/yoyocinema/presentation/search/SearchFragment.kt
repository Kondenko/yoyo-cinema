package com.vladimirkondenko.yoyocinema.presentation.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.jakewharton.rxbinding2.widget.RxSearchView
import com.vladimirkondenko.yoyocinema.R
import com.vladimirkondenko.yoyocinema.di.Ui
import com.vladimirkondenko.yoyocinema.domain.search.model.Film
import com.vladimirkondenko.yoyocinema.presentation.BaseFragment
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.concurrent.TimeUnit

class SearchFragment : BaseFragment() {

    private val vm: SearchViewModel by viewModel()

    private val uiScheduler: Scheduler by inject(Ui)

    private val filmAdapter: FilmAdapter by inject { parametersOf(context as Any) }

    private val filmClicks = PublishSubject.create<Film>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_recyclerview_movies.layoutManager = GridLayoutManager(context, 2)
        search_recyclerview_movies.adapter = filmAdapter
        viewDisposables += RxSearchView.queryTextChanges(searchView)
                .subscribeOn(uiScheduler)
                .observeOn(uiScheduler)
                .doOnNext { if (it.isEmpty()) vm.onSearchCleared() }
                .filter { it.length > 2 }
                .debounce(250, TimeUnit.MILLISECONDS)
                .map { it.toString() }
                .subscribe(vm::search)
        viewDisposables += filmAdapter.itemClicks.debounce(300, TimeUnit.MILLISECONDS).subscribe(filmClicks::onNext)
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

    /**
     * Get an obsER
     */
    fun filmClicks() = filmClicks as Observable<Film>

}
