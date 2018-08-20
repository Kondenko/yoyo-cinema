package com.vladimirkondenko.yoyocinema.di.modules

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vladimirkondenko.yoyocinema.domain.search.model.Film
import com.vladimirkondenko.yoyocinema.presentation.common.FilmAdapter
import io.reactivex.subjects.PublishSubject
import org.koin.dsl.module.module

val filmModule = module {
    factory { (context: Context) -> FilmAdapter(context) }
    factory { (context: Context) -> GridLayoutManager(context, 2) as RecyclerView.LayoutManager }
    factory { PublishSubject.create<Film>() }
}