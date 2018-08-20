package com.vladimirkondenko.yoyocinema.data.favorites.repository

import com.vladimirkondenko.yoyocinema.data.favorites.dao.FavoritesDao
import com.vladimirkondenko.yoyocinema.data.favorites.model.FilmDetailsModel
import io.reactivex.Completable

class FavoritesRepository(private val favoritesDao: FavoritesDao) {

    fun addToFavorites(film: FilmDetailsModel): Completable = Completable.fromAction {
        favoritesDao.favorite(film)
    }

    fun removeFromFavorites(film: FilmDetailsModel): Completable = Completable.fromAction {
        favoritesDao.unfavorite(film)
    }

    fun isFavorite(id: Int) = favoritesDao.isFavorite(id)

    fun getFavorite(id: Int) = favoritesDao.getFavorite(id)

    fun getFavorites() = favoritesDao.getFavorites()

}