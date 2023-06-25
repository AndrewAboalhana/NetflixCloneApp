package com.aa.netflixclone.repository.mappers

import com.aa.netflixclone.data.local.database.entities.MovieDatabaseDto
import com.aa.netflixclone.domain.models.movie.MovieEntity

fun MovieEntity.toMovieDto(): MovieDatabaseDto{
    return MovieDatabaseDto(
            id = id,
            title = title,
            originalLanguage = originalLanguage,
            overview = overview,
            imageUrl = imageUrl,
            date = releaseDate
        )

}