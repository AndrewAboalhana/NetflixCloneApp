package com.aa.netflixclone.domain.models.search

data class MultiSearchEntity (
    val page: Int? = 0,
    val results: List<ResultEntity> = listOf(),
    val totalPages: Int? = 0,
    val totalResults: Int? = 0
        )