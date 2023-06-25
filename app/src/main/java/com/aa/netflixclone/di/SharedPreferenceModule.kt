package com.aa.netflixclone.di

import android.content.Context
import android.content.SharedPreferences
import com.aa.netflixclone.data.local.SharedPreferenceImpl
import com.aa.netflixclone.repository.dataSources.SharedPreferenceService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {

    @Provides
    @Singleton
    fun provideFoodSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)


    @Provides
    @Singleton
    fun provideSharedPrefService(sharedPreferenceImpl: SharedPreferenceImpl): SharedPreferenceService {
        return sharedPreferenceImpl
    }
}