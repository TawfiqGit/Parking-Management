package com.tawfiqdev.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.tawfiqdev.domain.enums.ReservationStatus

@Entity(
    tableName = "reservation",
    foreignKeys = [
        ForeignKey(entity = VehicleEntity::class, parentColumns = ["id"], childColumns = ["vehicle_id"], onDelete = ForeignKey.RESTRICT),
        ForeignKey(entity = ParkingEntity::class, parentColumns = ["id"], childColumns = ["parking_id"], onDelete = ForeignKey.RESTRICT),
        ForeignKey(entity = UserEntity::class, parentColumns = ["id"], childColumns = ["user_id"], onDelete = ForeignKey.RESTRICT),
        ForeignKey(entity = ParkingSpotEntity::class, parentColumns = ["id"], childColumns = ["spot_id"], onDelete = ForeignKey.SET_NULL),
    ],
    indices = [Index("vehicle_id"), Index("parking_id"), Index("spot_id"),Index("user_id"),]
)
data class ReservationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "user_id") val userId: Long = 0L,
    @ColumnInfo(name = "vehicle_id") val vehicleId: Long,
    @ColumnInfo(name = "parking_id") val parkingId: Long,
    @ColumnInfo(name = "spot_id") val spotId: Long?,
    @ColumnInfo(name = "starts_at") val startsAt: Long,
    @ColumnInfo(name = "ends_at") val endsAt: Long,
    @ColumnInfo(name = "status") val status: ReservationStatus = ReservationStatus.ACTIVE,
    @ColumnInfo(name = "expected_price_cents") val expectedPriceCents: Long? = null,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis()
)