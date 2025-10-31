package com.tawfiqdev.model

data class VehicleModel(
    val marque: String,
    val model: String,
    val version: String,
    val color: String,
    val year: Int,
    val energy: String,
    val lengthMeters: Double?,
    val widthMeters: Double?,
    val heightMeters: Double?,
    val wheelbaseMeters: Double?,
    val weightVadFinKg: Int?,
)
