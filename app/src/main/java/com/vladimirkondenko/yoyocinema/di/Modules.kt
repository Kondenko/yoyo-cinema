package com.vladimirkondenko.yoyocinema.di

import com.vladimirkondenko.yoyocinema.di.modules.MainModule
import com.vladimirkondenko.yoyocinema.di.modules.NetworkModule

/**
 * Creating a mutable list of all required modules
 * so that we can replace and add modules in tests.
 */
fun getModules() = mutableListOf(
        MainModule.create(),
        NetworkModule.create()
)