package com.tawfiqdev.domain.model

import com.tawfiqdev.domain.enums.ReservationStatus

data class Reservation(
    val userId: Long,
    val vehicleId: Long?,
    val parkingId: Long?,
    val spotId: Long? = null,
    val startsAt: Long?,
    val endsAt: Long?,
    val status: ReservationStatus,
    val expectedPriceCents: Long? = null,
    val createdAt: Long?
)

data class ReservationSummary(
    val reservationId: Long,
    val status: ReservationStatus,
    val vehicleLabel: String,
    val parkingName: String,
    val parkingAddress: String,
    val spotLabel: String?,
    val timeRange: String,
    val expectedPrice: String?
)