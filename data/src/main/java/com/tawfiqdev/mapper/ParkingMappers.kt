package com.tawfiqdev.mapper

import com.tawfiqdev.model.Parking
import com.tawfiqdev.database.entity.ParkingEntity

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