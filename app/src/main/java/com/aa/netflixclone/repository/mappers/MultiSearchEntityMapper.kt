package com.aa.netflixclone.repository.mappers

import com.aa.netflixclone.data.remote.resources.MultiSearchResource
import com.aa.netflixclone.data.remote.resources.Result
import com.aa.netflixclone.domain.models.search.MultiSearchEntity
import com.aa.netflixclone.domain.models.search.ResultEntity

fun MultiSearchResource.toMultiSearchEntity(): MultiSearchEntity{
    return MultiSearchEntity(
        page = page,
        results = results.toResultEntity(),
        totalPages = totalPages,
        totalResults = totalResults
    )
}

private fun List<Result>.toResultEntity(): List<ResultEntity>{
    return this.map {
        ResultEntity(
            id = it.id,
            mediaType = it.mediaType,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            overview = it.overview,
            releaseDate = it.releaseDate,
            posterPath = it.posterPath
        )
    }
}