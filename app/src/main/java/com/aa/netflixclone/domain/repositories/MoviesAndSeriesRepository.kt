package com.aa.netflixclone.domain.repositories

import com.aa.netflixclone.domain.models.movie.MovieDetailsEntity
import com.aa.netflixclone.domain.models.movie.MovieEntity
import com.aa.netflixclone.domain.models.search.MultiSearchEntity
import com.aa.netflixclone.domain.models.series.SeriesDetailsEntity
import com.aa.netflixclone.domain.models.series.SeriesEntity
import kotlinx.coroutines.flow.Flow

interface MoviesAndSeriesRepository {

    suspend fun getTrendingMovies(
        media_type: String = "movie",
        time_window: String = "week",
    ): List<MovieEntity>

    suspend fun getTrendingSeries(
        media_type: String = "tv",
        time_window: String = "week",
    ): List<SeriesEntity>

    suspend fun getMoviesDetails(movieId: Int): MovieDetailsEntity

    suspend fun getSeriesDetails(movieId: Int): SeriesDetailsEntity

    suspend fun saveCachingTimeStamp(key: String, cachingTime: Long)

    suspend fun getLastCachingTimeStamp(key: String): Long

    suspend fun multiSearch(query: String): MultiSearchEntity

    suspend fun getTrendingMoviesFromLocal(): Flow<List<MovieEntity>>

    suspend fun getTrendingSeriesFromLocal(): Flow<List<SeriesEntity>>

    suspend fun refreshTrendingMovies(movies: List<MovieEntity>)

    suspend fun refreshTrendingSeries(movies: List<SeriesEntity>)
}