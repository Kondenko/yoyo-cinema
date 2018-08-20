package com.vladimirkondenko.yoyocinema.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.vladimirkondenko.yoyocinema.R
import retrofit2.Retrofit
import java.util.*

inline fun <reified T> Retrofit.create(): T = this.create(T::class.java)

inline fun <reified T : Activity> Activity.startActivity(extras: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    extras?.let { intent.putExtras(extras) }
    startActivity(intent)
}

inline fun <reified T> Gson.fromJson(json: String)  = this.fromJson<T>(json, object: com.google.gson.reflect.TypeToken<T>(){}.type)

inline fun View.showErrorSnackbar(@StringRes message: Int, crossinline onRetry: () -> Unit) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
    snackbar.setAction(R.string.all_action_retry) {
        onRetry.invoke()
        snackbar.dismiss()
    }
}

fun <T> Array<T>.getRandomItem() = this[Random().nextInt(this.size - 1)]