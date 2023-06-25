package com.aa.netflixclone.domain.usecases

import com.aa.netflixclone.domain.repositories.MoviesAndSeriesRepository
import javax.inject.Inject

class GetLastCashingTimeUseCase @Inject constructor(
    private val moviesAndSeriesRepository: MoviesAndSeriesRepository
) {

    suspend operator fun invoke(key: String): Long {
        return moviesAndSeriesRepository.getLastCachingTimeStamp(key)
    }

}