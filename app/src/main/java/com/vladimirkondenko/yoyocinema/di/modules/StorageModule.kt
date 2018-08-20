package com.vladimirkondenko.yoyocinema.di.modules

import androidx.room.Room
import com.vladimirkondenko.yoyocinema.data.AppDatabase
import com.vladimirkondenko.yoyocinema.utils.databaseName
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val storageModule = module {
    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, databaseName).build() }
}