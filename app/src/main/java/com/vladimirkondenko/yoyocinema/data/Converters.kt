package com.vladimirkondenko.yoyocinema.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.vladimirkondenko.yoyocinema.data.favorites.model.ListWrapper
import com.vladimirkondenko.yoyocinema.utils.fromJson

/**
 * Helps storing nested list objects in the database as JSON strings.
 * This is not a very good approach, but the app doesn't have to store long lists,
 * so it's probably fine.
 */
object Converters {

    val gson = Gson() // Can't use DI here because this class is created automatically

    @TypeConverter
    @JvmStatic
    fun listWrapperToJson(listWrapper: ListWrapper?): String {
        listWrapper?.let {
            return gson.toJson(listWrapper.list)
        }
        return "{[]}" // empty
    }

    @TypeConverter
    @JvmStatic
    fun jsonToListWrapper(json: String): ListWrapper = ListWrapper(list = gson.fromJson(json))

}
