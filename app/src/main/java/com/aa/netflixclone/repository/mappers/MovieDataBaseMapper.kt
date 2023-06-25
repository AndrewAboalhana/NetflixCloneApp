package com.aa.netflixclone.repository.mappers

import com.aa.netflixclone.data.local.database.entities.MovieDatabaseDto
import com.aa.netflixclone.domain.models.movie.MovieEntity

fun List<MovieEntity>.toMoviesDatabase():List<MovieDatabaseDto>{
    return this.map {
        MovieDatabaseDto(
            id = it.id,
            title = it.title,
            originalLanguage = it.originalLanguage,
            overview = it.overview,
            imageUrl = it.imageUrl,
            date = it.releaseDate
        )
    }
}