package com.tawfiqdev.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "parking_level",
    indices = [Index(value = ["parking_id", "name"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = ParkingEntity::class,
            parentColumns = ["id"],
            childColumns = ["parking_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ParkingLevelEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "parking_id") val parkingId: Long,
    val name: String,   // "B2", "P1", "R+1"
    val ordinal: Int
)