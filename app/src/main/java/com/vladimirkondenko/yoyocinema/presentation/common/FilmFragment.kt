package com.vladimirkondenko.yoyocinema.presentation.common

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vladimirkondenko.yoyocinema.di.Ui
import com.vladimirkondenko.yoyocinema.domain.search.model.Film
import com.vladimirkondenko.yoyocinema.presentation.BaseFragment
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.concurrent.TimeUnit

abstract class FilmFragment : BaseFragment() {

    protected val uiScheduler: Scheduler by inject(Ui)

    protected val filmAdapter: FilmAdapter by inject { parametersOf(context as Any) }

    protected val layoutManager: RecyclerView.LayoutManager by inject { parametersOf(context as Any) }

    private val filmClicks: PublishSubject<Film> by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDisposables += filmAdapter.itemClicks.debounce(300, TimeUnit.MILLISECONDS).subscribe(filmClicks::onNext)
    }

    fun setupRecyclerView(recyclerView: RecyclerView) {
        if (recyclerView.layoutManager == null) recyclerView.layoutManager = layoutManager
        if (recyclerView.adapter == null) recyclerView.adapter = filmAdapter
    }

    /**
     * Get an observable of the film list item clicks
     */
    fun filmClicks() = filmClicks as Observable<Film>

}