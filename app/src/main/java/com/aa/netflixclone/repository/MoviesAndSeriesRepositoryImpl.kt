package com.aa.netflixclone.repository

import com.aa.netflixclone.data.local.database.entities.MovieDatabaseDto
import com.aa.netflixclone.data.local.database.entities.SeriesDatabaseDto
import com.aa.netflixclone.domain.models.movie.MovieDetailsEntity
import com.aa.netflixclone.domain.models.movie.MovieEntity
import com.aa.netflixclone.domain.models.search.MultiSearchEntity
import com.aa.netflixclone.domain.models.series.SeriesDetailsEntity
import com.aa.netflixclone.domain.models.series.SeriesEntity
import com.aa.netflixclone.domain.repositories.MoviesAndSeriesRepository
import com.aa.netflixclone.repository.dataSources.LocalDataSource
import com.aa.netflixclone.repository.dataSources.RemoteDataSource
import com.aa.netflixclone.repository.dataSources.SharedPreferenceService
import com.aa.netflixclone.repository.mappers.toMovieDetailsEntity
import com.aa.netflixclone.repository.mappers.toMovieDto
import com.aa.netflixclone.repository.mappers.toMovieEntity
import com.aa.netflixclone.repository.mappers.toMoviesEntity
import com.aa.netflixclone.repository.mappers.toMultiSearchEntity
import com.aa.netflixclone.repository.mappers.toSeriesDetails
import com.aa.netflixclone.repository.mappers.toSeriesDetailsEntity
import com.aa.netflixclone.repository.mappers.toSeriesDto
import com.aa.netflixclone.repository.mappers.toSeriesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesAndSeriesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val sharedPreferenceImpl: SharedPreferenceService
): MoviesAndSeriesRepository {

    override suspend fun getTrendingMovies(
        media_type: String,
        time_window: String,
    ): List<MovieEntity> {
       return remoteDataSource.getTrendingMovies().results.toMovieEntity()
    }

    override suspend fun getTrendingSeries(
        media_type: String,
        time_window: String,
    ): List<SeriesEntity> {
       return remoteDataSource.getTrendingSeries().results.toSeriesEntity()
    }

    override suspend fun getMoviesDetails(movieId: Int): MovieDetailsEntity {
        return remoteDataSource.getMovieDetails(movieId).toMovieDetailsEntity()
    }

    override suspend fun getSeriesDetails(seriesId: Int): SeriesDetailsEntity {
        return remoteDataSource.getSeriesDetails(seriesId).toSeriesDetailsEntity()
    }

    override suspend fun saveCachingTimeStamp(key: String, cachingTime: Long) {
        sharedPreferenceImpl.saveLastCachingTimeStamp(key, cachingTime)
    }

    override suspend fun getLastCachingTimeStamp(key: String): Long {
        return sharedPreferenceImpl.getLastCachingTime(key)
    }

    override suspend fun multiSearch(query: String): MultiSearchEntity {
        return remoteDataSource.multiSearch(query).toMultiSearchEntity()
    }

    override suspend fun getTrendingMoviesFromLocal(): Flow<List<MovieEntity>> {
        return localDataSource.getTrendingMovies().map(List<MovieDatabaseDto>::toMoviesEntity)
    }

    override suspend fun getTrendingSeriesFromLocal(): Flow<List<SeriesEntity>> {
        return localDataSource.getTrendingSeries().map (List<SeriesDatabaseDto>::toSeriesDetails)
    }

    override suspend fun refreshTrendingMovies(movies: List<MovieEntity>) {
       localDataSource.insertTrendingMovies(movies.map(MovieEntity::toMovieDto))
    }

    override suspend fun refreshTrendingSeries(series: List<SeriesEntity>) {
        localDataSource.insertTrendingSeries(series.map(SeriesEntity::toSeriesDto))
    }
}