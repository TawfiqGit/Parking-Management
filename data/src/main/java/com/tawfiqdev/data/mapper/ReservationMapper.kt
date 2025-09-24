package com.tawfiqdev.data.mapper

import com.tawfiqdev.data.room.entity.ReservationEntity
import com.tawfiqdev.data.room.entity.ReservationWithDetails
import com.tawfiqdev.domain.model.Reservation
import com.tawfiqdev.domain.model.ReservationSummary

fun ReservationEntity.toDomain(): Reservation =
    Reservation(
        userId = userId,
        vehicleId = vehicleId,
        parkingId = parkingId,
        spotId = spotId,
        startsAt = startsAt,
        endsAt = endsAt,
        status = status,
        expectedPriceCents = expectedPriceCents,
        createdAt = createdAt
    )


fun ReservationWithDetails.toUI(): ReservationSummary {
    val vehicleLabel = "${vehicle.marque} ${vehicle.model} • ${vehicle.registrationPlate}"
    val spotLabel = spot?.let { "Place ${it.spotCode}" }

    val timeRange = buildString {
        val s = java.time.Instant.ofEpochMilli(reservation.startsAt)
        val e = java.time.Instant.ofEpochMilli(reservation.endsAt)
        val fmt = java.time.format.DateTimeFormatter.ofPattern("HH:mm")
            .withZone(java.time.ZoneId.systemDefault())
        append(fmt.format(s)).append(" → ").append(fmt.format(e))
    }

    val expectedPrice = reservation.expectedPriceCents?.let { cents ->
        val euros = cents / 100.0
        "€" + String.format("%.2f", euros)
    }

    return ReservationSummary(
        reservationId = reservation.id,
        status = reservation.status,
        vehicleLabel = vehicleLabel,
        parkingName = parking.name,
        parkingAddress = parking.address,
        spotLabel = spotLabel,
        timeRange = timeRange,
        expectedPrice = expectedPrice
    )
}