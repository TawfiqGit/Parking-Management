package com.tawfiqdev.parkingmanagement.data.mapper

import com.tawfiqdev.parkingmanagement.data.room.entity.VehicleEntity
import com.tawfiqdev.parkingmanagement.domain.model.Vehicle

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