package com.tawfiqdev.mapper

import com.tawfiqdev.model.LocationSelection
import com.tawfiqdev.database.entity.LocationEntity

fun LocationEntity.toDomain() = LocationSelection(
    id = id,
    title = title,
    address = address,
    latitude = latitude,
    longitude = longitude
)