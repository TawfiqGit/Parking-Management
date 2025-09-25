package com.tawfiqdev.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_locations")
data class RecentLocationEntity(
    @PrimaryKey(autoGenerate = true)  val id: Int = 0,
    val locationId: Int,
    val lastUsedAt: Long = System.currentTimeMillis()
)