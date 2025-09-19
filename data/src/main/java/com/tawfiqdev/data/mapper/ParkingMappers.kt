package com.tawfiqdev.data.mapper

import com.tawfiqdev.data.room.entity.ParkingEntity
import com.tawfiqdev.domain.model.Parking

fun ParkingEntity.toDomain(
    pricePerHour: Double? = null,
    rating: Double? = null,
    distanceMins: Int? = null,
    spots: Int? = null,
    imageRes: Int? = null,
    imageUrl: String? = null
) = Parking(
    id = id,
    name = name,
    address = address,
    city = city,
    country = country,
    latitude = latitude,
    longitude = longitude,
    open24_7 = open24_7,
    pricePerHour = pricePerHour,
    rating = rating,
    distanceMins = distanceMins,
    spots = spots,
    imageRes = imageRes,
    imageUrl = imageUrl
)

fun Parking.toEntity(): ParkingEntity = ParkingEntity(
    id = if (id == 0L) 0 else id,
    name = name,
    address = address,
    city = city,
    country = country,
    latitude = latitude,
    longitude = longitude,
    open24_7 = open24_7
)