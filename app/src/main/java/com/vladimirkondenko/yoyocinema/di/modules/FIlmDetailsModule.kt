package com.vladimirkondenko.yoyocinema.di.modules

import com.vladimirkondenko.yoyocinema.data.filmdetails.repository.FilmDetailsRepository
import com.vladimirkondenko.yoyocinema.data.filmdetails.service.FilmDetailsService
import com.vladimirkondenko.yoyocinema.di.Ui
import com.vladimirkondenko.yoyocinema.di.Worker
import com.vladimirkondenko.yoyocinema.di.defaultLocale
import com.vladimirkondenko.yoyocinema.domain.filmdetails.usecase.GetDetails
import com.vladimirkondenko.yoyocinema.presentation.filmdetails.FilmDetailsViewModel
import com.vladimirkondenko.yoyocinema.utils.create
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val filmDetailsModule = module {
    viewModel { FilmDetailsViewModel(get()) }
    single { GetDetails(get(), get(Ui), get(Worker), get(defaultLocale)) }
    single { FilmDetailsRepository(get()) }
    single { get<Retrofit>().create<FilmDetailsService>() }
}