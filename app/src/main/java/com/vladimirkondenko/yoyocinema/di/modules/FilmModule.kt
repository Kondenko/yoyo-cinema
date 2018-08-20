package com.vladimirkondenko.yoyocinema.di.modules

import android.content.Context
import com.vladimirkondenko.yoyocinema.domain.search.model.Film
import com.vladimirkondenko.yoyocinema.presentation.common.FilmAdapter
import io.reactivex.subjects.PublishSubject
import org.koin.dsl.module.module

val filmModule = module {
    factory { (context: Context) -> FilmAdapter(context) }
    factory { PublishSubject.create<Film>() }
}