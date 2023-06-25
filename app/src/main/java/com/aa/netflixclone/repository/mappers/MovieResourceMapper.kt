package com.aa.netflixclone.repository.mappers

import com.aa.netflixclone.data.remote.resources.Result
import com.aa.netflixclone.data.remote.resources.TrendingResource
import com.aa.netflixclone.domain.models.TrendingEntity
import com.aa.netflixclone.domain.models.search.ResultEntity

fun TrendingEntity.toMovieResource():TrendingResource{
    return TrendingResource(
        page = page,
        totalPages = totalPages,
        totalResults = totalResults,
        results = results.toListResult()
    )
}

private fun List<ResultEntity?>?.toListResult(): List<Result?>?{
    return this?.map {
        Result(
            id = it?.id,
            title = it?.originalTitle,
            posterPath = it?.posterPath,
            releaseDate = it?.releaseDate,
            overview = it?.overview,
            mediaType = it?.mediaType,
            originalLanguage = it?.originalLanguage,
        )
    }
}