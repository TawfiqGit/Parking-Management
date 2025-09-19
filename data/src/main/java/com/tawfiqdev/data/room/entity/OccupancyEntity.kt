package com.tawfiqdev.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "occupancy",
    indices = [Index(value = ["spot_id", "at_millis"])],
    foreignKeys = [
        ForeignKey(
            entity = ParkingSpotEntity::class,
            parentColumns = ["id"],
            childColumns = ["spot_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class OccupancyEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "spot_id") val spotId: Long,
    val state: String,   // FREE / OCCUPIED / RESERVED
    val source: String,  // SENSOR / APP / STAFF
    @ColumnInfo(name = "at_millis") val atMillis: Long = System.currentTimeMillis()
)