package com.aa.netflixclone.di

import android.content.Context
import androidx.room.Room
import com.aa.netflixclone.data.local.database.NetflixDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NetflixDatabase {
        return Room.databaseBuilder(
            context,
            NetflixDatabase::class.java,
            "NETFLIX_DATABASE"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: NetflixDatabase) = movieDatabase.movieDao()

    @Provides
    @Singleton
    fun provideTvShowDao(movieDatabase: NetflixDatabase) = movieDatabase.seriesDao()
}
