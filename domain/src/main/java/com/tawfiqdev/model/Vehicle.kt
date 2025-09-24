package com.tawfiqdev.model

import com.tawfiqdev.enums.StatusCar

data class Vehicle(
    val id: Long,
    val registrationPlate: String,
    val marque: String,
    val model: String,
    val siege: Int,
    val statusCar: StatusCar = StatusCar.AVAILABLE,
)