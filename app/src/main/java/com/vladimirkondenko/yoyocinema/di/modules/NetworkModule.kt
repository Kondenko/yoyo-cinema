package com.vladimirkondenko.yoyocinema.di.modules

import com.vladimirkondenko.yoyocinema.di.Ui
import com.vladimirkondenko.yoyocinema.di.Worker
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    fun create() = module {
        single(Ui) { AndroidSchedulers.mainThread() }
        single(Worker) { Schedulers.io() }
        single { RxJava2CallAdapterFactory.create() }
        single { GsonConverterFactory.create() }
        single {
            Retrofit.Builder()
                    .addCallAdapterFactory(get())
                    .addConverterFactory(get())
                    .baseUrl("https://api.themoviedb.org/3/")
                    .build()
        }
    }

}