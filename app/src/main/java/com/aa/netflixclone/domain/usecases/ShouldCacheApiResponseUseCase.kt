package com.aa.netflixclone.domain.usecases

import javax.inject.Inject

class ShouldCacheApiResponseUseCase @Inject constructor(
    private val getLastCachingTimeUseCae: GetLastCashingTimeUseCase,
    private val getCurrentTimestampUseCase: GetCurrentTimesTampUseCase,
    private val refreshLastCachingTimeStamp: RefreshLastCashingTimesTampUseCase
) {

    suspend operator fun invoke(key: String): Boolean {
        val lastCachingTime = getLastCachingTimeUseCae(key)
        val currentTime = getCurrentTimestampUseCase()
        val timeDifference = currentTime - lastCachingTime
        val twelveHoursInMillis = 12 * 60 * 60 * 1000
        return (timeDifference > twelveHoursInMillis).also { if (it) refreshLastCachingTimeStamp(key) }
    }

}