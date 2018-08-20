package com.vladimirkondenko.yoyocinema.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vladimirkondenko.yoyocinema.data.favorites.dao.FavoritesDao
import com.vladimirkondenko.yoyocinema.data.favorites.model.FilmDetailsModel

@Database(entities = [FilmDetailsModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao

}