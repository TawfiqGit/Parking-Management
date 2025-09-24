package com.tawfiqdev.mapper

import com.tawfiqdev.model.Vehicle
import com.tawfiqdev.room.entity.VehicleEntity

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