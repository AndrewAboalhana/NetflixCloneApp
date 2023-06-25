package com.aa.netflixclone.repository.mappers

import com.aa.netflixclone.data.remote.resources.Result
import com.aa.netflixclone.domain.models.series.SeriesEntity

fun List<SeriesEntity>.toSeriesResultResource(): List<Result> {
    return this.map {
        Result(
            id = it.id,
            originalTitle = it.originalName ,
            posterPath = it.imageUrl,
            overview = it.overview,
            popularity = it.popularity,
            originalLanguage = it.originalLanguage ,
            voteAverage = it.voteAverage ,
            releaseDate = it.firstAirDate
        )
    }
}