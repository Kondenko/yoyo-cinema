package com.vladimirkondenko.yoyocinema.di

import android.content.Context
import com.vladimirkondenko.yoyocinema.di.modules.MainModule
import com.vladimirkondenko.yoyocinema.di.modules.NetworkModule
import com.vladimirkondenko.yoyocinema.di.modules.SearchModule

/**
 * Creates a mutable list of all required modules
 * so that we can replace and add modules in tests.
 */
fun getModules(context: Context) = mutableListOf(
        MainModule.create(),
        NetworkModule.create(),
        SearchModule.create()
)