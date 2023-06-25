package com.aa.netflixclone.repository.mappers

import com.aa.netflixclone.data.local.database.entities.MovieDatabaseDto
import com.aa.netflixclone.domain.models.movie.MovieEntity

fun List<MovieDatabaseDto>.toMoviesEntity():List<MovieEntity>{
    return this.map {
        MovieEntity(
            id = it.id,
            title = it.title,
            originalLanguage = it.originalLanguage,
            overview = it.overview,
            imageUrl = it.imageUrl,
            releaseDate = it.date,
            voteAverage = 0.0,
            popularity = 0.0

        )
    }
}