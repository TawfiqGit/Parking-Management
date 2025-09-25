package com.tawfiqdev.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.tawfiqdev.enums.PaymentStatus

@Entity(
    tableName = "payments",
    foreignKeys = [
        ForeignKey(
            entity = ReservationEntity::class,
            parentColumns = ["id"],
            childColumns = ["reservation_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("reservation_id")]
)
data class PaymentEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "reservation_id") val reservationId: Long,
    @ColumnInfo(name = "provider_ref") val providerRef: String?,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    val amount: Double,
    val currency: String = "EUR",
    val method: String, // "card", "apple_pay", "cash", ...
    val status: PaymentStatus,
)