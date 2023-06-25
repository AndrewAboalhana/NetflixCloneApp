package com.aa.netflixclone.data.local

import android.content.SharedPreferences
import com.aa.netflixclone.repository.dataSources.SharedPreferenceService
import javax.inject.Inject

class SharedPreferenceImpl @Inject constructor(
    private val shearedPreferences: SharedPreferences
): SharedPreferenceService {

    override fun saveLastCachingTimeStamp(key: String, time: Long) {
        shearedPreferences.edit {
            putLong(key, time)
        }
    }

    override fun getLastCachingTime(key: String): Long {
        return shearedPreferences.getLong(key, 0)
    }


    private fun SharedPreferences.edit(
        commit: Boolean = false,
        action: SharedPreferences.Editor.() -> Unit
    ) {
        val editor = edit()
        action(editor)
        if (commit) {
            editor.commit()
        } else {
            editor.apply()
        }
    }

}