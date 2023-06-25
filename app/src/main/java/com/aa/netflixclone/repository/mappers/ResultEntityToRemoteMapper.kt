package com.aa.netflixclone.repository.mappers

import com.aa.netflixclone.data.remote.resources.Result
import com.aa.netflixclone.domain.models.search.ResultEntity

fun List<ResultEntity>.toResultResource():List<Result>{
    return this.map {
        Result(
            id = it.id,
            mediaType = it.mediaType,
            originalTitle = it.originalTitle,
            originalLanguage = it.originalLanguage,
            overview = it.overview,
            releaseDate = it.releaseDate,
            posterPath = it.posterPath
        )
    }
}