package com.aa.netflixclone.domain.usecases

import com.aa.netflixclone.domain.models.search.MultiSearchEntity
import com.aa.netflixclone.domain.repositories.MoviesAndSeriesRepository
import javax.inject.Inject

class MultiSearchUseCase @Inject constructor(
    private val moviesAndSeriesRepository: MoviesAndSeriesRepository,
) {

    suspend operator fun invoke(query: String): MultiSearchEntity {
        return moviesAndSeriesRepository.multiSearch(query)
    }
}