package com.tawfiqdev.parkingmanagement.data.mapper

import com.tawfiqdev.parkingmanagement.data.room.entity.LocationEntity
import com.tawfiqdev.parkingmanagement.domain.model.LocationSelection

fun LocationEntity.toDomain() = LocationSelection(
    id = id,
    title = title,
    address = address,
    latitude = latitude,
    longitude = longitude
)