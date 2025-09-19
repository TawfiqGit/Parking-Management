package com.tawfiqdev.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "parking_spots" ,
    indices = [
        Index(value = ["parking_id", "spot_code"], unique = true),
        Index(value = ["level_id"]),
        Index(value = ["spot_type_id"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = ParkingEntity::class,
            parentColumns = ["id"],
            childColumns = ["parking_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ParkingLevelEntity::class,
            parentColumns = ["id"],
            childColumns = ["level_id"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = SpotTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["spot_type_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class ParkingSpotEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "parking_id") val parkingId: Long,
    @ColumnInfo(name = "level_id") val levelId: Long?,
    @ColumnInfo(name = "spot_code") val spotCode: String, // ex "B2-045"
    @ColumnInfo(name = "spot_type_id") val spotTypeId: Int,
    @ColumnInfo(name = "is_active") val isActive: Boolean = true,
    @ColumnInfo(name = "has_ev_charger") val hasEvCharger: Boolean = false
)