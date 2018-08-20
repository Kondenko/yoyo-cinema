package com.vladimirkondenko.yoyocinema.domain.favorites

import com.vladimirkondenko.yoyocinema.data.favorites.model.FilmDetailsModel
import com.vladimirkondenko.yoyocinema.data.favorites.model.ListWrapper
import com.vladimirkondenko.yoyocinema.data.favorites.repository.FavoritesRepository
import com.vladimirkondenko.yoyocinema.domain.UseCaseCompletable
import com.vladimirkondenko.yoyocinema.domain.films.model.FilmDetails
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single

class SetFavorite(private val favoritesRepository: FavoritesRepository, uiScheduler: Scheduler, workerScheduler: Scheduler)
    : UseCaseCompletable<SetFavorite.Params>(uiScheduler, workerScheduler) {

    data class Params(val film: FilmDetails, val setFavorite: Boolean)

    override fun build(params: Params): Completable {
        return Single.just(params.film)
                .map {
                    FilmDetailsModel(it.id, it.title, it.overview, ListWrapper(it.genres), it.runtime, it.releaseDate, it.voteAverage, it.voteCount, it.posterPath, it.backdropPath)
                }
                .flatMapCompletable {
                    if (params.setFavorite) favoritesRepository.addToFavorites(it)
                    else favoritesRepository.removeFromFavorites(it)
                }
    }

}