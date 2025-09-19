package com.tawfiqdev.data.mapper

import com.tawfiqdev.data.room.entity.VehicleEntity
import com.tawfiqdev.domain.model.Vehicle

fun VehicleEntity.toDomain(): Vehicle = Vehicle(
    id = id,
    registrationPlate = registrationPlate,
    model = model,
    statusCar = statusCar,
    marque = marque,
    siege = siege
)

fun Vehicle.toEntity(): VehicleEntity = VehicleEntity(
    id = id,
    registrationPlate = registrationPlate,
    siege = siege,
    model = model,
    marque = marque,
    statusCar = statusCar
)