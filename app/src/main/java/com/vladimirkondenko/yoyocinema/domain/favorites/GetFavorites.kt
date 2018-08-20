package com.vladimirkondenko.yoyocinema.domain.favorites

import com.vladimirkondenko.yoyocinema.data.favorites.repository.FavoritesRepository
import com.vladimirkondenko.yoyocinema.domain.UseCaseSingle
import com.vladimirkondenko.yoyocinema.domain.search.model.Film
import io.reactivex.Scheduler
import io.reactivex.Single

class GetFavorites(private val favoritesRepository: FavoritesRepository, uiScheduler: Scheduler, workerScheduler: Scheduler)
    : UseCaseSingle<Nothing?, List<Film>>(uiScheduler, workerScheduler) {

    override fun build(params: Nothing?): Single<List<Film>> {
        /*
         I should have used the detail models in the list so the app doesn't have to download them again
         when the user enter the detail screen.
         */
        return favoritesRepository.getFavorites()
                .map {
                    it.map { Film(it.id, it.title, it.releaseDate.takeLast(4).toInt(), it.voteAverage, it.posterPath) }
                }
    }


}