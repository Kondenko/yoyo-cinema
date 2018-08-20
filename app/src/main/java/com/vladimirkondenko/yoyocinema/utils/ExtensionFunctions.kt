package com.vladimirkondenko.yoyocinema.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.gson.Gson
import retrofit2.Retrofit

inline fun <reified T> Retrofit.create(): T = this.create(T::class.java)

inline fun <reified T : Activity> Activity.startActivity(extras: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    extras?.let { intent.putExtras(extras) }
    startActivity(intent)
}

inline fun <reified T> Gson.fromJson(json: String)  = this.fromJson<T>(json, object: com.google.gson.reflect.TypeToken<T>(){}.type)