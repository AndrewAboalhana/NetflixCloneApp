package com.aa.netflixclone.repository.dataSources

import com.aa.netflixclone.data.local.database.entities.MovieDatabaseDto
import com.aa.netflixclone.data.local.database.entities.SeriesDatabaseDto
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun getTrendingMovies(): Flow<List<MovieDatabaseDto>>
    suspend fun insertTrendingMovies(movies: List<MovieDatabaseDto>)
    suspend fun getTrendingSeries(): Flow<List<SeriesDatabaseDto>>
    suspend fun insertTrendingSeries(series: List<SeriesDatabaseDto>)
}