package com.tawfiqdev.data.mapper

import com.tawfiqdev.data.room.entity.ParkingEntity
import com.tawfiqdev.domain.model.Parking

fun ParkingEntity.toDomain(): Parking = Parking(
    id = id,
    name= name,
    category=category,
    pricePerHour=pricePerHour,
    rating=rating,
    distanceMins=distanceMins,
    spots=spots,
    imageRes= imageRes,
    imageUrl = imageUrl
)

fun Parking.toEntity(): ParkingEntity = ParkingEntity(
    id = id,
    name= name,
    category=category,
    pricePerHour=pricePerHour,
    rating=rating,
    distanceMins=distanceMins,
    spots=spots,
    imageRes= imageRes,
    imageUrl = imageUrl
)