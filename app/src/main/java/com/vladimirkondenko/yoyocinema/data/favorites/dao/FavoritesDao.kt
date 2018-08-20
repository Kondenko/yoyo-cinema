package com.vladimirkondenko.yoyocinema.data.favorites.dao

import androidx.room.*
import com.vladimirkondenko.yoyocinema.data.favorites.model.FilmDetailsModel
import io.reactivex.Single

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun favorite(film: FilmDetailsModel)

    @Delete
    fun unfavorite(film: FilmDetailsModel)

    @Query("SELECT EXISTS (SELECT 1 FROM favorites WHERE id == :id)")
    fun isFavorite(id: Int): Single<Boolean>

    @Query("SELECT * FROM favorites")
    fun getFavorites(): Single<List<FilmDetailsModel>>

    @Query("SELECT * FROM favorites WHERE id == :id")
    fun getFavorite(id: Int): Single<FilmDetailsModel>

}