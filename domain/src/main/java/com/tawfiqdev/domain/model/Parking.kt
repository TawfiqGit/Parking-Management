package com.tawfiqdev.domain.model

import com.tawfiqdev.domain.enums.Category

data class Parking (
    val id: Long,
    val name: String,
    val category: Category = Category.CAR,
    val address: String,
    val city: String? = null,
    val country: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val open24_7: Boolean = true,
    val pricePerHour: Double? = null,
    val rating: Double? = null,
    val distanceMins: Int? = null,
    val spots: Int? = null,
    val imageRes: Int? = null,
    val imageUrl: String? = null
)