package com.vladimirkondenko.yoyocinema.di.modules

import com.vladimirkondenko.yoyocinema.data.AppDatabase
import com.vladimirkondenko.yoyocinema.data.favorites.repository.FavoritesRepository
import com.vladimirkondenko.yoyocinema.di.Ui
import com.vladimirkondenko.yoyocinema.di.Worker
import com.vladimirkondenko.yoyocinema.domain.favorites.SetFavorite
import org.koin.dsl.module.module

val favoritesModule = module {
    single { get<AppDatabase>().favoritesDao() }
    factory { FavoritesRepository(get()) }
    factory { SetFavorite(get(), get(Ui), get(Worker)) }
}