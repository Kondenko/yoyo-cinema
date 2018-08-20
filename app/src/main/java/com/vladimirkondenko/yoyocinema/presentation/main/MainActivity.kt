package com.vladimirkondenko.yoyocinema.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.transaction
import com.vladimirkondenko.yoyocinema.R
import com.vladimirkondenko.yoyocinema.domain.search.model.Film
import com.vladimirkondenko.yoyocinema.presentation.favorites.FavoritesFragment
import com.vladimirkondenko.yoyocinema.presentation.filmdetails.FilmDetailsActivity
import com.vladimirkondenko.yoyocinema.presentation.search.SearchFragment
import com.vladimirkondenko.yoyocinema.utils.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val searchFragment: SearchFragment by inject()

    private val favoritesFragment: FavoritesFragment by inject()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchFragment.filmClicks().subscribe(this::openDetails)
        favoritesFragment.filmClicks().subscribe(this::openDetails)
        main_bottomnav.setOnNavigationItemSelectedListener {
            supportFragmentManager.transaction {
                val fragment = when (it.itemId) {
                    R.id.item_bottomnav_search -> {
                        searchFragment
                    }
                    R.id.item_bottomnav_favorites -> {
                        favoritesFragment
                    }
                    else -> throw IllegalArgumentException("Unknown bottom navigation item id: ${it.itemId}")
                }
                replace(R.id.container, fragment)
            }
            true
        }
        if (savedInstanceState == null) main_bottomnav.selectedItemId = R.id.item_bottomnav_search
    }

    private fun openDetails(film: Film) = startActivity<FilmDetailsActivity>(bundleOf(FilmDetailsActivity.argId to film.id))

}
