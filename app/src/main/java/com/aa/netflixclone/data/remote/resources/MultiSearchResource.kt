package com.aa.netflixclone.data.remote.resources


import com.google.gson.annotations.SerializedName

data class MultiSearchResource(
    @SerializedName("page")
    val page: Int? = 0,
    @SerializedName("results")
    val results: List<Result> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int? = 0,
    @SerializedName("total_results")
    val totalResults: Int? = 0
)