package com.aa.netflixclone.repository.mappers

import com.aa.netflixclone.data.remote.resources.series_details.Season
import com.aa.netflixclone.domain.models.series.SeasonEntity

fun List<SeasonEntity>.toSeasonResource(): List<Season>{
    return this.map {
        Season(
            id = it.id,
            airDate = it.airDate,
            episodeCount = it.episodeCount,
            name = it.name,
            overview = it.overview,
            posterPath = it.imageUrl,
            seasonNumber = it.seasonNumber
        )
    }
}