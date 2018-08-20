package com.vladimirkondenko.yoyocinema.di.modules

import android.annotation.SuppressLint
import com.vladimirkondenko.yoyocinema.di.defaultLocale
import com.vladimirkondenko.yoyocinema.presentation.favorites.FavoritesFragment
import com.vladimirkondenko.yoyocinema.presentation.search.SearchFragment
import org.koin.dsl.module.module
import java.util.*

@SuppressLint("ConstantLocale")
val mainModule = module {
    single { SearchFragment() }
    single { FavoritesFragment() }
    factory(defaultLocale) { Locale.getDefault() }
}
