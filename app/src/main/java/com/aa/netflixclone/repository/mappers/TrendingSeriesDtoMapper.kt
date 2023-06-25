package com.aa.netflixclone.repository.mappers

import com.aa.netflixclone.data.local.database.entities.SeriesDatabaseDto
import com.aa.netflixclone.domain.models.series.SeriesEntity

fun List<SeriesDatabaseDto>.toSeriesDetails(): List<SeriesEntity>{
    return this.map {
        SeriesEntity(
            id = it.id,
            imageUrl = it.imageUrl,
            firstAirDate = it.date,
            popularity = it.popularity,
            voteAverage = it.voteAverage,
            overview = it.overview,
            originalLanguage = it.originalLanguage,
            originalName = it.title
        )
    }
}