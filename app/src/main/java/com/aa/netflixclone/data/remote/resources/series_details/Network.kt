package com.aa.netflixclone.data.remote.resources.series_details


import com.google.gson.annotations.SerializedName

data class Network(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("logo_path")
    val logoPath: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: String? = null
)