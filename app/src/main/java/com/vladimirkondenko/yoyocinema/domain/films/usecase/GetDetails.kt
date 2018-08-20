package com.vladimirkondenko.yoyocinema.domain.films.usecase

import com.vladimirkondenko.yoyocinema.data.favorites.repository.FavoritesRepository
import com.vladimirkondenko.yoyocinema.data.filmdetails.repository.FilmDetailsRepository
import com.vladimirkondenko.yoyocinema.domain.UseCaseSingle
import com.vladimirkondenko.yoyocinema.domain.films.model.FilmDetails
import com.vladimirkondenko.yoyocinema.utils.ImageAddress
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.rxkotlin.zipWith
import java.text.SimpleDateFormat
import java.util.*

/**
 * Fetches a film's details and transforms it into a View-consumable model.
 * Returns a pair with the movie and a boolean indicating if the movie is marked as favorite
 */
class GetDetails(
        private val detailsRepository: FilmDetailsRepository,
        private val favoritesRepository: FavoritesRepository,
        uiScheduler: Scheduler,
        workerScheduler: Scheduler,
        locale: Locale
) : UseCaseSingle<Int, Pair<FilmDetails, Boolean>>(uiScheduler, workerScheduler) {

    private val runtimeFormat = SimpleDateFormat("HH:mm", locale)

    private val releaseDateFormatIn = SimpleDateFormat("yyyy-MM-dd", locale)

    private val releaseDateFormatOut = SimpleDateFormat("MM.dd.yyyy", locale)

    override fun build(id: Int): Single<Pair<FilmDetails, Boolean>> {
        // TODO Return the value from the database if it's already there
        return detailsRepository.getDetails(id).map {
                    FilmDetails(
                            it.id ?: id,
                            it.title ?: "",
                            it.overview ?: "",
                            (it.genres ?: emptyList()).filter { it.name != null }.map { it.name!! },
                            it.runtime?.formatRuntime() ?: "",
                            it.releaseDate?.formatReleaseDate() ?: "",
                            it.voteAverage ?: 0f,
                            it.voteCount ?: 0,
                            it.posterPath?.let { ImageAddress.getUrlForImage(it, ImageAddress.Size.SMALL) } ?: "",
                            it.backdropPath?.let { ImageAddress.getUrlForImage(it, ImageAddress.Size.LARGE) } ?: ""
                    )
                }
                .zipWith(favoritesRepository.isFavorite(id))
                { film: FilmDetails, isFavorite: Boolean -> film to isFavorite }
    }

    private fun Int.formatRuntime() = runtimeFormat.format(Date(this.toLong()))

    private fun String.formatReleaseDate() = releaseDateFormatOut.format(releaseDateFormatIn.parse(this))

}