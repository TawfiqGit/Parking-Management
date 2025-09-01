package com.tawfiqdev.parkingmanagement.domain.model

enum class StatusCar {
    AVAILABLE,
    UNAVAILABLE,
    MAINTENANCE,
}

data class Vehicle(
    val id: Long,
    val registrationPlate: String,
    val marque: String,
    val model: String,
    val siege: Int,
    val statusCar: StatusCar = StatusCar.AVAILABLE,
)