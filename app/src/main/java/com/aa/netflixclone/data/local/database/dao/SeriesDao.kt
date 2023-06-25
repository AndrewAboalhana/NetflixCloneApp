package com.aa.netflixclone.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aa.netflixclone.data.local.database.entities.SeriesDatabaseDto
import kotlinx.coroutines.flow.Flow

@Dao
interface SeriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingSeries(popularSeries: List<SeriesDatabaseDto>)

    @Query("SELECT * FROM TRENDING_SERIES")
    fun getTrendingSeries(): Flow<List<SeriesDatabaseDto>>
}