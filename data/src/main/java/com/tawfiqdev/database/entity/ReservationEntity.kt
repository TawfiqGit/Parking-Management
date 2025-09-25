package com.tawfiqdev.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.tawfiqdev.enums.ReservationStatus

@Entity(
    tableName = "reservations",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = VehicleEntity::class,
            parentColumns = ["id"],
            childColumns = ["vehicle_id"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = ParkingEntity::class,
            parentColumns = ["id"],
            childColumns = ["parking_id"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = ParkingSpotEntity::class,
            parentColumns = ["id"],
            childColumns = ["spot_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index("user_id"),
        Index(value = ["parking_id","start_time","end_time"]),
        Index(value = ["spot_id","start_time","end_time"])
    ]
)
data class ReservationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "user_id") val userId: Long,
    @ColumnInfo(name = "vehicle_id") val vehicleId: Long?,
    @ColumnInfo(name = "parking_id") val parkingId: Long,
    @ColumnInfo(name = "spot_id") val spotId: Long?,
    @ColumnInfo(name = "start_time") val startTime: Long,
    @ColumnInfo(name = "end_time") val endTime: Long,
    @ColumnInfo(name = "total_amount") val totalAmount: Double = 0.0,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    val status: ReservationStatus = ReservationStatus.PENDING,
)