package com.aa.netflixclone.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aa.netflixclone.data.local.database.dao.MovieDao
import com.aa.netflixclone.data.local.database.dao.SeriesDao
import com.aa.netflixclone.data.local.database.entities.MovieDatabaseDto
import com.aa.netflixclone.data.local.database.entities.SeriesDatabaseDto


@Database(
    entities =[
        MovieDatabaseDto::class,
        SeriesDatabaseDto::class
    ],
     version = 1,
    exportSchema = false
)


abstract class NetflixDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun seriesDao(): SeriesDao
}