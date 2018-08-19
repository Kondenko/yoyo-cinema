package com.vladimirkondenko.yoyocinema.data.filmdetails.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Genre(
        @SerializedName("id")
        @Expose
        var id: Int? = null,
        @SerializedName("name")
        @Expose
        var name: String? = null
)