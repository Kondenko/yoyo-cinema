package com.vladimirkondenko.yoyocinema.data.favorites.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vladimirkondenko.yoyocinema.data.favorites.model.FilmDetailsModel
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface FavoritesDao {

    @Insert
    fun favorite(film: FilmDetailsModel)

    @Delete
    fun unfavorite(film: FilmDetailsModel)

    @Query("SELECT EXISTS (SELECT 1 FROM favorites WHERE id == :id)")
    fun isFavorite(id: Int): Single<Boolean>

    @Query("SELECT * FROM favorites")
    fun getFavorites(): Flowable<FilmDetailsModel>

    @Query("SELECT * FROM favorites WHERE id == :id")
    fun getFavorite(id: Int): Single<FilmDetailsModel>

}