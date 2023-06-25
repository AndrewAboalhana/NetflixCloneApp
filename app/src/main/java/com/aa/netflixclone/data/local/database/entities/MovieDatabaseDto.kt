package com.aa.netflixclone.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TRENDING_MOVIES")
data class MovieDatabaseDto (
    @PrimaryKey val id: Int,
    val title: String,
    val originalLanguage: String,
    val overview: String,
    val imageUrl: String,
    val date: String,
)