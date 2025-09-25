package com.tawfiqdev.database.entity

import androidx.room.Entity
import androidx.room.Fts4

@Fts4
@Entity(tableName = "locations_fts")
data class LocationFts(
    val title: String,
    val address: String
)
