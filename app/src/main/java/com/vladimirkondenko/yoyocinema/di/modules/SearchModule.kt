package com.vladimirkondenko.yoyocinema.di.modules

import android.content.Context
import com.vladimirkondenko.yoyocinema.data.search.repository.SearchRepository
import com.vladimirkondenko.yoyocinema.data.search.serivce.SearchService
import com.vladimirkondenko.yoyocinema.di.Ui
import com.vladimirkondenko.yoyocinema.di.Worker
import com.vladimirkondenko.yoyocinema.domain.search.usecase.SearchMovie
import com.vladimirkondenko.yoyocinema.presentation.search.FilmAdapter
import com.vladimirkondenko.yoyocinema.presentation.search.SearchViewModel
import com.vladimirkondenko.yoyocinema.utils.create
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

object SearchModule {

    fun create() = module {
        single { (context: Context) -> FilmAdapter(context) }
        viewModel { SearchViewModel(get()) }
        single { SearchMovie(get(), get(Ui), get(Worker)) }
        single { SearchRepository(get()) }
        single { get<Retrofit>().create<SearchService>() }
    }

}