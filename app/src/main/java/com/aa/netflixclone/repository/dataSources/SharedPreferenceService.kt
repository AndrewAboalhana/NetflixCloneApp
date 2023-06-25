package com.aa.netflixclone.repository.dataSources

interface SharedPreferenceService {

    fun saveLastCachingTimeStamp(key: String, time: Long)

    fun getLastCachingTime(key: String): Long
}