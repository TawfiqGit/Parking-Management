package com.tawfiqdev.parkingmanagement.data.mapper

import com.tawfiqdev.parkingmanagement.data.room.entity.LocationEntity
import com.tawfiqdev.parkingmanagement.data.room.entity.VehicleEntity
import com.tawfiqdev.parkingmanagement.domain.model.LocationSelection
import com.tawfiqdev.parkingmanagement.domain.model.Vehicle
import kotlin.Int

fun LocationEntity.toDomain(): LocationSelection = LocationSelection(
    id = id,
    title= title,
    address= address,
    latitude= latitude,
    longitude = longitude,
    isCurrentLocation = isCurrentLocation
)

fun LocationSelection.toEntity(): LocationEntity = LocationEntity(
    id = id,
    title= title,
    address= address,
    latitude= latitude,
    longitude = longitude,
    isCurrentLocation = isCurrentLocation
)