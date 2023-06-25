package com.aa.netflixclone.data

import android.content.SharedPreferences
import com.aa.netflixclone.data.local.SharedPreferenceImpl
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test

class SharedPreferenceImplTest {

    private lateinit var sharedPreferenceImpl: SharedPreferenceImpl
    private lateinit var mockSharedPreferences: SharedPreferences
    private lateinit var mockEditor: SharedPreferences.Editor

    @Before
    fun setUp() {
        mockSharedPreferences = mock()
        mockEditor = mock()
        whenever(mockSharedPreferences.edit()).thenReturn(mockEditor)
        sharedPreferenceImpl = SharedPreferenceImpl(mockSharedPreferences)
    }

    @Test
    fun saveLastCachingTimeStamp_shouldSaveTimeToSharedPreferences() {
        // Arrange
        val key = "caching_key"
        val time = 123456L

        // Act
        sharedPreferenceImpl.saveLastCachingTimeStamp(key, time)

        // Assert
        verify(mockEditor).putLong(key, time)
        verify(mockEditor).apply()
    }

    @Test
    fun getLastCachingTime_shouldReturnTimeFromSharedPreferences() {
        // Arrange
        val key = "caching_key"
        val expectedTime = 123456L
        val defaultTime = 0L

        // Mock the behavior of SharedPreferences
        whenever(mockSharedPreferences.getLong(key, defaultTime)).thenReturn(expectedTime)

        // Act
        val result = sharedPreferenceImpl.getLastCachingTime(key)

        // Assert
        verify(mockSharedPreferences).getLong(key, defaultTime)
        assert(result == expectedTime)
    }
}

