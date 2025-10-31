package com.tawfiqdev.model

data class Vehicle(
    val id: Long,
    val userId: Long,
    val registrationPlate: String,
    val model: VehicleModel,
)