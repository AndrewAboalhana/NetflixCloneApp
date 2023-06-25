package com.aa.netflixclone.domain.models.movie


data class MovieEntity(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val overview: String,
    val popularity: Double,
    val originalLanguage: String,
    val releaseDate: String,
    val voteAverage: Double,
)