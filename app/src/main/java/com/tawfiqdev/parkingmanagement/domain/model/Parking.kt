package com.tawfiqdev.parkingmanagement.domain.model

import com.tawfiqdev.parkingmanagement.data.room.entity.Category

data class Parking (
    val id: Int = 0,
    val name: String,
    val category: Category = Category.CAR,
    val pricePerHour: Double,
    val rating: Double,
    val distanceMins: Int,
    val spots: Int,
    val imageRes: Int? = null,
    val imageUrl: String? = null
)