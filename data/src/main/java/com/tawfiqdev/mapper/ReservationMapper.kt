package com.tawfiqdev.mapper

import com.tawfiqdev.database.entity.ReservationEntity
import com.tawfiqdev.database.relations.ReservationFull
import com.tawfiqdev.model.Reservation
import com.tawfiqdev.model.ReservationDetails

fun ReservationFull.toDomain(): ReservationDetails =
    ReservationDetails(
        reservation = reservation.toDomain(),
        user = user.toDomain(),
        vehicle = vehicle?.toDomain(),
        parking = parking.toDomain(),
        spot = spot?.toDomain(),
        payments = payments.map { it.toDomain() },
        entryExitLog = entryExitLog?.toDomain()
    )


fun ReservationEntity.toDomain() = Reservation(
    id = id,
    userId = userId,
    vehicleId = vehicleId,
    parkingId = parkingId,
    spotId = spotId,
    startTime = startTime,
    endTime = endTime,
    status = status,
    createdAt = createdAt,
    totalAmount = totalAmount,
)

fun Reservation.toEntity() = ReservationEntity(
    id = id,
    userId = userId,
    vehicleId = vehicleId,
    parkingId = parkingId,
    spotId = spotId,
    startTime = startTime,
    endTime = endTime,
    status = status,
    createdAt = createdAt,
    totalAmount = totalAmount,
)