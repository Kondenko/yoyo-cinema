package com.vladimirkondenko.yoyocinema.presentation.filmdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vladimirkondenko.yoyocinema.R
import com.vladimirkondenko.yoyocinema.presentation.BaseFragment
import com.vladimirkondenko.yoyocinema.presentation.main.FilmDetailsState
import org.koin.androidx.viewmodel.ext.android.viewModel


class FilmDetailsFragment : BaseFragment() {

    private val TAG = this.javaClass.simpleName

    private val argId = "id"

    private val vm: FilmDetailsViewModel by viewModel()

    companion object {
        fun create(id: Int) = with(FilmDetailsFragment()) {
            this.arguments = Bundle().apply { putInt(argId, id) }
            this
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_film_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.observeState(this) {
            Log.i(TAG, it.toString())
            when (it) {
                is FilmDetailsState.Success -> {
                    Log.i(TAG, it.model.toString())
                }
            }
        }
        if (arguments != null) vm.getDetails(arguments!!.getInt(argId))
        else vm.onDetailsNotFound()
    }

}
