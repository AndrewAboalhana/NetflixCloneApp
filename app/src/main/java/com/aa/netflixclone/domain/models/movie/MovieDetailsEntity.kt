package com.aa.netflixclone.domain.models.movie

data class MovieDetailsEntity(
    val id: Int?,
    val imageUrl: String?,
    val adult: Boolean?,
    val imdbId: String?,
    val overview: String?,
    val date: String?,
    val runtime: Int?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
)
