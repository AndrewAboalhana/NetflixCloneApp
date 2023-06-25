package com.aa.netflixclone.repository.mappers

import com.aa.netflixclone.data.remote.resources.Result
import com.aa.netflixclone.domain.models.movie.MovieEntity

fun List<MovieEntity>.toResultResource():List<Result>{
    return this.map {
        Result(
            id = it.id,
            title = it.title,
            overview = it.overview,
            posterPath = it.imageUrl,
            popularity = it.popularity,
            originalLanguage = it.originalLanguage,
            releaseDate = it.releaseDate,
            voteAverage = it.voteAverage
        )
    }
}