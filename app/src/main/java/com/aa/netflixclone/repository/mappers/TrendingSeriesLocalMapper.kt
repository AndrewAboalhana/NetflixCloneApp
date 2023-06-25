package com.aa.netflixclone.repository.mappers

import com.aa.netflixclone.data.local.database.entities.SeriesDatabaseDto
import com.aa.netflixclone.domain.models.series.SeriesEntity

fun SeriesEntity.toSeriesDto(): SeriesDatabaseDto{
    return SeriesDatabaseDto(
        id = id,
        title = originalName,
        originalLanguage = originalLanguage,
        overview = overview,
        imageUrl = imageUrl,
        date = firstAirDate,
        popularity = popularity,
        voteAverage = voteAverage
    )
}