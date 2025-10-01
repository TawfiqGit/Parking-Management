package com.tawfiqdev.model

import com.tawfiqdev.enums.ReservationStatus

data class Reservation(
    val id: Long,
    val status: ReservationStatus,
    val userId: Long,
    val vehicleId: Long?,
    val parkingId: Long,
    val spotId: Long?,
    val startTime: Long,
    val endTime: Long,
    val totalAmount: Double,
    val createdAt: Long
)