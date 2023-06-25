package com.aa.netflixclone.domain.models.series


data class SeriesEntity(
    val id: Int,
    val originalName:String,
    val imageUrl: String,
    val originalLanguage: String,
    val overview: String,
    val firstAirDate: String,
    val popularity: Double,
    val voteAverage: Double,
)