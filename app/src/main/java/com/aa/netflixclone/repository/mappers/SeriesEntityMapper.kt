package com.aa.netflixclone.repository.mappers

import com.aa.netflixclone.data.remote.resources.Result
import com.aa.netflixclone.domain.models.series.SeriesEntity
import com.aa.netflixclone.repository.utils.orEmpty
import com.aa.netflixclone.repository.utils.orZero

fun List<Result?>?.toSeriesEntity(): List<SeriesEntity>{
    return this!!.map {
        SeriesEntity(
            id = it?.id.orZero(),
            imageUrl = it?.posterPath.orEmpty(),
            firstAirDate = it?.releaseDate.orEmpty(),
            popularity = it?.popularity.orZero(),
            voteAverage = it?.voteAverage.orZero(),
            overview = it?.overview.orEmpty(),
            originalLanguage = it?.originalLanguage.orEmpty(),
            originalName = it?.originalTitle.orEmpty()
        )
    }
}