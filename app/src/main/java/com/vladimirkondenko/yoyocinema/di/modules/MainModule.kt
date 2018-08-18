package com.vladimirkondenko.yoyocinema.di.modules

import com.vladimirkondenko.yoyocinema.presentation.search.SearchFragment
import org.koin.dsl.module.module

object MainModule {

    fun create() = module {
        single { SearchFragment() }
    }

}