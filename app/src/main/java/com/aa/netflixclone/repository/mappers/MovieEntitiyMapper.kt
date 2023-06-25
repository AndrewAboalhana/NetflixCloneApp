package com.aa.netflixclone.repository.mappers

import com.aa.netflixclone.data.remote.resources.Result
import com.aa.netflixclone.domain.models.movie.MovieEntity
import com.aa.netflixclone.repository.utils.orEmpty
import com.aa.netflixclone.repository.utils.orZero

fun List<Result?>?.toMovieEntity(): List<MovieEntity> {
    return this?.map {
        MovieEntity(
            id = it?.id.orZero(),
            title = it?.title.orEmpty(),
            imageUrl = it?.posterPath.orEmpty(),
            popularity = it?.popularity.orZero(),
            releaseDate = it?.releaseDate.orEmpty(),
            voteAverage = it?.voteAverage.orZero(),
            originalLanguage = it?.originalLanguage.orEmpty(),
            overview = it?.overview.orEmpty()
        )
    }!!
}