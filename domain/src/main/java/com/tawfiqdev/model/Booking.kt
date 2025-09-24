package com.tawfiqdev.model

data class Booking(
    val title: String,
    val city: String,
    val country: String,
    val pricePerHour: String,
    val rating: Double,
    val category: String = "Car Parking",
    val imageUrl: String
)
