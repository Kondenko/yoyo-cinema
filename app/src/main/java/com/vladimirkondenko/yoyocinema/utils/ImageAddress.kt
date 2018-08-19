package com.vladimirkondenko.yoyocinema.utils

object ImageAddress {

    enum class Size(val size: String) {
        SMALL("w185"),
        LARGE("w780"),
        ORIGINAL("original")
    }

    fun getUrlForImage(imageName: String, size: Size) = "http://image.tmdb.org/t/p/${size.size}/$imageName"

}
