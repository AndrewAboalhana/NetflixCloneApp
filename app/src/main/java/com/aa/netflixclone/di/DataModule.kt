package com.aa.netflixclone.di

import com.aa.netflixclone.data.local.LocalDataSourceImpl
import com.aa.netflixclone.data.remote.RemoteDataSourceImpl
import com.aa.netflixclone.domain.repositories.MoviesAndSeriesRepository
import com.aa.netflixclone.repository.MoviesAndSeriesRepositoryImpl
import com.aa.netflixclone.repository.dataSources.LocalDataSource
import com.aa.netflixclone.repository.dataSources.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {


    @Binds
    @Singleton
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindMovieAndSeriesRepository(MovieAndSeriesImpl: MoviesAndSeriesRepositoryImpl): MoviesAndSeriesRepository

}