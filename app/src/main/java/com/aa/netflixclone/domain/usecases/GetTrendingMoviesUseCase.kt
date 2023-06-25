package com.aa.netflixclone.domain.usecases

import com.aa.netflixclone.domain.models.movie.MovieEntity
import com.aa.netflixclone.domain.repositories.MoviesAndSeriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrendingMoviesUseCase @Inject constructor(
    private val moviesAndSeriesRepository: MoviesAndSeriesRepository,
    private val shouldCacheApiResponseUseCase: ShouldCacheApiResponseUseCase,
) {

    suspend operator fun invoke(): Flow<List<MovieEntity>>{
        if (shouldCacheApiResponseUseCase("trending_movies")){
            refreshLocalTrendingMovies()
        }
        return moviesAndSeriesRepository.getTrendingMoviesFromLocal()
    }

   private suspend fun getTrendingMovies(): List<MovieEntity>{
       return moviesAndSeriesRepository.getTrendingMovies("movie","week")
   }

    private suspend fun refreshLocalTrendingMovies(){
        val trendingMovies = getTrendingMovies()
        moviesAndSeriesRepository.refreshTrendingMovies(trendingMovies)
    }
}