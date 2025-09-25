package com.tawfiqdev.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.tawfiqdev.enums.SpotType

@Entity(
    tableName = "parking_spots",
    foreignKeys = [
        ForeignKey(
            entity = ParkingEntity::class,
            parentColumns = ["id"],
            childColumns = ["parking_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("parking_id"),
        Index(value = ["parking_id", "spot_number"], unique = true)
    ]
)
data class ParkingSpotEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "parking_id") val parkingId: Long,
    @ColumnInfo(name = "spot_number") val spotNumber: String,
    val type: SpotType = SpotType.STANDARD,
    @ColumnInfo(name = "is_active") val isActive: Boolean = true
)