package com.vladimirkondenko.yoyocinema.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.vladimirkondenko.yoyocinema.R
import com.vladimirkondenko.yoyocinema.presentation.filmdetails.FilmDetailsFragment
import com.vladimirkondenko.yoyocinema.presentation.search.SearchFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val searchFragment: SearchFragment by inject()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                replace(R.id.container, searchFragment)
            }
            searchFragment.filmClicks().subscribe {
                val tag = it.id.toString()
                if (supportFragmentManager.findFragmentByTag(tag) == null) {
                    supportFragmentManager.transaction {
                        addToBackStack(tag)
                        replace(R.id.container, FilmDetailsFragment.create(it.id), tag)
                    }
                }
            }
        }
    }

}
