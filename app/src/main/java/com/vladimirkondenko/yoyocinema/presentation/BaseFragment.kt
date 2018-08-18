package com.vladimirkondenko.yoyocinema.presentation

import android.util.Log
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

/**
 * Contains the logic common for all fragments.
 */
open class BaseFragment : Fragment() {

    protected val viewDisposables = CompositeDisposable()

    override fun onDestroyView() {
        if (viewDisposables.size() == 0) Log.w("BaseFragment","Your view CompositeDisposable is empty. Make sure all RxBinding subscriptions are added here.")
        viewDisposables.clear()
        super.onDestroyView()
    }

}