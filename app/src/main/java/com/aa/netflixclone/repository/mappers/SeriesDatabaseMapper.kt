package com.aa.netflixclone.repository.mappers

import com.aa.netflixclone.data.local.database.entities.SeriesDatabaseDto
import com.aa.netflixclone.domain.models.series.SeriesEntity

fun List<SeriesEntity>.toSeriesDatabase(): List<SeriesDatabaseDto>{
    return this.map {
        SeriesDatabaseDto(
            id = it.id,
            title = it.originalName,
            originalLanguage = it.originalLanguage,
            overview = it.overview,
            imageUrl = it.imageUrl,
            date = it.firstAirDate,
            popularity = it.popularity,
            voteAverage = it.voteAverage
        )
    }
}