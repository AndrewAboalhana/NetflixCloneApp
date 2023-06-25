package com.aa.netflixclone.domain.usecases

import com.aa.netflixclone.domain.models.series.SeriesEntity
import com.aa.netflixclone.domain.repositories.MoviesAndSeriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrendingSeriesUseCase @Inject constructor(
    private val moviesAndSeriesRepository: MoviesAndSeriesRepository,
    private val shouldCacheApiResponseUseCase: ShouldCacheApiResponseUseCase
) {
    suspend operator fun invoke(): Flow<List<SeriesEntity>> {
        if (shouldCacheApiResponseUseCase("trending_series")){
            refreshLocalTrendingSeries()
        }
        return moviesAndSeriesRepository.getTrendingSeriesFromLocal()
    }

    private suspend fun getTrendingSeries(): List<SeriesEntity>{
        return moviesAndSeriesRepository.getTrendingSeries()
    }

    private suspend fun refreshLocalTrendingSeries(){
        val trendingMovies = getTrendingSeries()
        moviesAndSeriesRepository.refreshTrendingSeries(trendingMovies)
    }
}