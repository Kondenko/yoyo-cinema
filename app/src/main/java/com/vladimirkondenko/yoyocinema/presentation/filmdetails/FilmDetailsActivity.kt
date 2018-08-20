package com.vladimirkondenko.yoyocinema.presentation.filmdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import com.squareup.picasso.Picasso
import com.vladimirkondenko.yoyocinema.R
import com.vladimirkondenko.yoyocinema.utils.showErrorSnackbar
import kotlinx.android.synthetic.main.fragment_film_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class FilmDetailsActivity : AppCompatActivity() {

    companion object {
        const val argId = "id"
        const val invalidId = -1
    }

    private val vm: FilmDetailsViewModel by viewModel()

    private var id: Int? = null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_film_details)
        if (intent?.extras != null) {
            (intent!!.extras!!.getInt(argId) ?: invalidId).let {
                id = it
                vm.getDetails(it)
            }
        } else {
            vm.onDetailsNotFound()
        }
        // Draw the image behind the status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setSupportActionBar(film_details_toolbar)
        supportActionBar?.run {
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
        vm.state(this) {
            when (it) {
                is FilmDetailsState.Success -> {
                    showContent(true)
                    film_details_progressbar.isVisible = false
                    film_details_imageview_error.isVisible = false
                    // Display the model
                    val film = it.film
                    film_details_checkbox_favorite.isChecked = it.isFavorite
                    Picasso.get().load(film.backdropPath).placeholder(R.drawable.ic_film_black_48dp).into(details_imageview_backdrop)
                    Picasso.get().load(film.posterPath).into(details_imageview_poster)
                    film_details_textview_title.text = film.title
                    film_details_textview_avg_rating.text = film.voteAverage.toString()
                    film_details_textview_reviews_number.text = getString(R.string.film_details_review_numbers_template, film.voteCount) ?: ""
                    if (film.releaseDate.isNotEmpty()) film_details_textview_release.text = getString(R.string.film_details_release_date_template, film.releaseDate)
                    else film_details_textview_release.isGone = true
                    if (film.genres.isNotEmpty()) film_details_textview_genres.text = film.genres.joinToString(", ")
                    else film_details_textview_genres.isGone = true
                    if (film.runtime.isNotEmpty()) film_details_textview_runtime.text = getString(R.string.film_details_runtime_template, film.runtime)
                    if (film.overview.isNotEmpty()) film_details_textview_overview.text = film.overview
                    else film_details_textview_overview.isGone = true
                }
                is FilmDetailsState.Loading -> {
                    film_details_progressbar.isVisible = true
                    showContent(false)
                    film_details_imageview_error.isVisible = false
                }
                is FilmDetailsState.Error -> {
                    film_details_root.showErrorSnackbar(R.string.film_details_error_message) {
                        id?.let(vm::getDetails)
                    }
                    film_details_imageview_error.isVisible = true
                    film_details_progressbar.isVisible = false
                    showContent(false)
                }
                is FilmDetailsState.FavoriteError -> {
                    Toast.makeText(this, R.string.film_details_favorite_error_message, Toast.LENGTH_LONG).show()
                }
            }
        }
        RxCompoundButton.checkedChanges(film_details_checkbox_favorite).skipInitialValue().subscribe { vm.setFavorite(it) }
    }

    private fun showContent(show: Boolean) {
        film_details_group_content.isVisible = show
        film_details_checkbox_favorite.isEnabled = show
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
