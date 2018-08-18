package com.vladimirkondenko.yoyocinema

import android.app.Application
import com.vladimirkondenko.yoyocinema.di.getModules
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, getModules())
    }

}