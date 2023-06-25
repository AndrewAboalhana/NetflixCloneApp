package com.aa.netflixclone.domain.usecases

import com.aa.netflixclone.domain.repositories.MoviesAndSeriesRepository
import javax.inject.Inject

class RefreshLastCashingTimesTampUseCase @Inject constructor(
    private val moviesAndSeriesRepository: MoviesAndSeriesRepository,
    private val getCurrentTimesTampUseCase: GetCurrentTimesTampUseCase
) {
    suspend operator fun invoke(key: String) {
        val currentTime = getCurrentTimesTampUseCase()
        moviesAndSeriesRepository.saveCachingTimeStamp(key, currentTime)
    }
}