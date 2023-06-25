package com.aa.netflixclone.data.local

import com.aa.netflixclone.data.local.database.dao.MovieDao
import com.aa.netflixclone.data.local.database.dao.SeriesDao
import com.aa.netflixclone.data.local.database.entities.MovieDatabaseDto
import com.aa.netflixclone.data.local.database.entities.SeriesDatabaseDto
import com.aa.netflixclone.repository.dataSources.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val seriesDao: SeriesDao,
) : LocalDataSource {

    override suspend fun getTrendingMovies(): Flow<List<MovieDatabaseDto>> {
        return movieDao.getTrendingMovies()
    }

    override suspend fun insertTrendingMovies(movies: List<MovieDatabaseDto>) {
        movieDao.insertTrendingMovies(movies)
    }

    override suspend fun getTrendingSeries(): Flow<List<SeriesDatabaseDto>> {
        return seriesDao.getTrendingSeries()
    }

    override suspend fun insertTrendingSeries(series: List<SeriesDatabaseDto>) {
        seriesDao.insertTrendingSeries(series)
    }
}