package com.tawfiqdev.model

import com.tawfiqdev.enums.Category

data class Parking (
    val id: Int = 0,
    val name: String,
    val category: Category = Category.CAR,
    val pricePerHour: Double,
    val rating: Double,
    val distanceMins: Int,
    val spots: Int,
    val imageRes: Int? = null ,
    val imageUrl: String? = null,
    val reviewCount: Int? = null,
    val address: String
)