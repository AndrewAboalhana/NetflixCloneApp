package com.aa.netflixclone.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aa.netflixclone.data.local.database.entities.MovieDatabaseDto
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingMovies(popularMovies: List<MovieDatabaseDto>)

     @Query("SELECT * FROM TRENDING_MOVIES")
     fun getTrendingMovies(): Flow<List<MovieDatabaseDto>>
}