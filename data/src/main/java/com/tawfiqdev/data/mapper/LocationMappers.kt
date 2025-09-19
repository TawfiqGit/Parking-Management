package com.tawfiqdev.data.mapper

import com.tawfiqdev.data.room.entity.LocationEntity
import com.tawfiqdev.domain.model.LocationSelection

fun LocationEntity.toDomain() = LocationSelection(
    id = id,
    title = title,
    address = address,
    latitude = latitude,
    longitude = longitude
)