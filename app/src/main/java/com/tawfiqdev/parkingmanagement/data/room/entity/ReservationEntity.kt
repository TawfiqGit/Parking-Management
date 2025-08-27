package com.tawfiqdev.parkingmanagement.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "reservation")
data class ReservationEntity (
    @PrimaryKey(autoGenerate = true) val id : Long = 0L,
    @ColumnInfo(name = "vehicleId") val vehicleId : Long,
    @ColumnInfo(name = "userId") val userId : Long,
    @ColumnInfo(name = "startTime") val startTime : Instant,
    @ColumnInfo(name = "endTime") val endTime : Instant,
    @ColumnInfo(name = "status") val statusRes: StatusRez = StatusRez.PENDING,
    @ColumnInfo(name = "notes") val notes : String? = null,
)

enum class StatusRez {
    PENDING,
    CONFIRMED,
    CANCELLED,
    COMPLETED
}