package com.aa.netflixclone.domain.models

import com.aa.netflixclone.domain.models.search.ResultEntity


data class TrendingEntity(
    val page: Int? = null,
    val results: List<ResultEntity?>? = null,
    val totalPages: Int? = null,
    val totalResults: Int? = null
)
