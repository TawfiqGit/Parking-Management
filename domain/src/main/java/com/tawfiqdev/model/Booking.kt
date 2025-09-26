package com.tawfiqdev.model

import com.tawfiqdev.enums.BookingStatus

data class Booking(
    val id: Long,
    val status: BookingStatus,
    val imageUrl: String,
    val categoryLabel: String = "Car Parking",
    val name: String,
    val city: String,
    val country: String,
    val pricePerHour: Double,
    val rating: Double
)
