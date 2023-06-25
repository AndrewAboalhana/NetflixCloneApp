package com.aa.netflixclone.repository.mappers

import com.aa.netflixclone.data.remote.resources.series_details.Season
import com.aa.netflixclone.data.remote.resources.series_details.SeriesDetailsResource
import com.aa.netflixclone.domain.models.series.SeasonEntity
import com.aa.netflixclone.domain.models.series.SeriesDetailsEntity

fun SeriesDetailsResource.toSeriesDetailsEntity(): SeriesDetailsEntity{
    return SeriesDetailsEntity(
        id = id,
        name = name,
        adult = adult,
        imageUrl = posterPath,
        firstAirDate = firstAirDate,
        numberOfEpisodes = numberOfEpisodes,
        numberOfSeasons = numberOfSeasons,
        overview = overview,
        popularity = popularity,
        seasons = seasons.toSeasonEntity(),
        type = type,
        voteAverage = voteAverage
    )
}

private fun List<Season?>?.toSeasonEntity(): List<SeasonEntity>?{
    return this?.map {
        SeasonEntity(
            id = it?.id,
            episodeCount = it?.episodeCount,
            name = it?.name,
            overview = it?.overview,
            imageUrl = it?.posterPath,
            seasonNumber = it?.seasonNumber,
            airDate = it?.airDate
        )
    }
}