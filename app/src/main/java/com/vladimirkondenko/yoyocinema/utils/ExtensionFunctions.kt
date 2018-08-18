package com.vladimirkondenko.yoyocinema.utils

import retrofit2.Retrofit

inline fun <reified T> Retrofit.create() = this.create(T::class.java)