package com.vladimirkondenko.yoyocinema.data.favorites.model

/**
 * A wrapper object that helps storing lists with Room (which apparently has trouble with them)
 */
data class ListWrapper(val list: List<String>)