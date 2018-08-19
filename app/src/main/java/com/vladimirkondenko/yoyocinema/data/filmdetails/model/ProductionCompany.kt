package com.vladimirkondenko.yoyocinema.data.filmdetails.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductionCompany(
        @SerializedName("id")
        @Expose
        var id: Int? = null,
        @SerializedName("logo_path")
        @Expose
        var logoPath: Any? = null,
        @SerializedName("name")
        @Expose
        var name: String? = null,
        @SerializedName("origin_country")
        @Expose
        var originCountry: String? = null
)
