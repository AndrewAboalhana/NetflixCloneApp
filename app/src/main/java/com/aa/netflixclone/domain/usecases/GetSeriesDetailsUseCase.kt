package com.aa.netflixclone.domain.usecases

import com.aa.netflixclone.domain.models.series.SeriesDetailsEntity
import com.aa.netflixclone.domain.repositories.MoviesAndSeriesRepository
import javax.inject.Inject

class GetSeriesDetailsUseCase @Inject constructor(
    private val moviesAndSeriesRepository: MoviesAndSeriesRepository
) {
    suspend operator fun invoke(id: Int): SeriesDetailsEntity{
        return moviesAndSeriesRepository.getSeriesDetails(id)
    }
}