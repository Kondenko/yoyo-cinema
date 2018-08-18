package com.vladimirkondenko.yoyocinema.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.vladimirkondenko.yoyocinema.R
import com.vladimirkondenko.yoyocinema.presentation.search.SearchFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val searchFragment: SearchFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                replace(R.id.container, searchFragment)
            }
        }
    }

}
