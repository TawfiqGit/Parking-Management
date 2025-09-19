package com.tawfiqdev.domain.model

import com.tawfiqdev.domain.enums.StatusCar

data class Vehicle(
    val id: Long,
    val registrationPlate: String,
    val marque: String,
    val model: String,
    val siege: Int,
    val statusCar: StatusCar = StatusCar.AVAILABLE,
)