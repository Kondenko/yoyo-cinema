package com.vladimirkondenko.yoyocinema.di

import com.vladimirkondenko.yoyocinema.di.modules.*

/**
 * A mutable list of all required modules, where we can replace and add modules whenever necessary.
 */
val modules = mutableListOf(
        mainModule,
        networkModule,
        storageModule,
        searchModule,
        filmDetailsModule,
        favoritesModule
)