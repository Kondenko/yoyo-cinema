package com.vladimirkondenko.yoyocinema.presentation.common

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
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

    private val filmClicks: PublishSubject<Film> by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDisposables += filmAdapter.itemClicks.debounce(300, TimeUnit.MILLISECONDS).subscribe(filmClicks::onNext)
    }

    fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = filmAdapter
    }

    /**
     * Get an observable of the film list item clicks
     */
    fun filmClicks(): Observable<Film> = filmClicks

}