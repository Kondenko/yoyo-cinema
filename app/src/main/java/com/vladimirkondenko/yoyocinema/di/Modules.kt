package com.vladimirkondenko.yoyocinema.di

import com.vladimirkondenko.yoyocinema.di.modules.filmDetailsModule
import com.vladimirkondenko.yoyocinema.di.modules.mainModule
import com.vladimirkondenko.yoyocinema.di.modules.networkModule
import com.vladimirkondenko.yoyocinema.di.modules.searchModule

/**
 * A mutable list of all required modules, where we can replace and add modules whenever necessary.
 */
val modules = mutableListOf(
        mainModule,
        networkModule,
        searchModule,
        filmDetailsModule
)