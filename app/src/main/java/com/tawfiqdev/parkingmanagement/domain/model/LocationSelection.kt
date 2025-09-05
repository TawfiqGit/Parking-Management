package com.tawfiqdev.parkingmanagement.domain.model

data class LocationSelection(
    val id: Int = 0,
    val title: String,
    val address: String,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val isCurrentLocation: Boolean = false
)