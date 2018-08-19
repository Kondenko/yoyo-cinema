package com.vladimirkondenko.yoyocinema.domain.search.usecase

import com.vladimirkondenko.yoyocinema.data.search.repository.SearchRepository
import com.vladimirkondenko.yoyocinema.domain.UseCaseSingle
import com.vladimirkondenko.yoyocinema.domain.search.model.Film
import io.reactivex.Scheduler
import io.reactivex.Single


/**
 * Query movies from the network and map them to View-consumable models.
 */
class SearchMovie(private val searchRepository: SearchRepository, uiScheduler: Scheduler, workerScheduler: Scheduler)
    : UseCaseSingle<SearchMovie.Params, List<Film>>(uiScheduler, workerScheduler) {

    data class Params(val query: String, val page: Int)

    override fun build(params: Params): Single<List<Film>> {
        return searchRepository.searchMovie(params.query, params.page)
                .flatMap {
                    if (it.results != null) Single.just(it.results)
                    else Single.error(NullPointerException("Films are not found"))
                }
                .map {
                    it.map {
                    val year = it.releaseDate?.slice(0..3)?.toInt() // awkwardly parsing the year from a yyyy-mm-dd formatted date TODO replace with proper parsing
                    val posterUrl = "http://image.tmdb.org/t/p/w185/${it.posterPath}"
                    Film(it.id?:0, it.title ?: "", year ?: -1, it.voteAverage ?: -1f, posterUrl)
                    }
                }
    }

}