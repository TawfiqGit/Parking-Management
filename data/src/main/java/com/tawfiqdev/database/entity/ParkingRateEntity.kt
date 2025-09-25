package com.tawfiqdev.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "parking_rates",
    foreignKeys = [
        ForeignKey(
            entity = ParkingEntity::class,
            parentColumns = ["id"],
            childColumns = ["parking_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("parking_id")]
)
data class ParkingRateEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "parking_id") val parkingId: Long,
    @ColumnInfo(name = "rule_name") val ruleName: String,
    @ColumnInfo(name = "day_mask") val dayMask: Int,
    @ColumnInfo(name = "start_minute") val startMinute: Int,
    @ColumnInfo(name = "end_minute") val endMinute: Int,
    @ColumnInfo(name = "price_per_hour") val pricePerHour: Double,
    val currency: String = "EUR"
)