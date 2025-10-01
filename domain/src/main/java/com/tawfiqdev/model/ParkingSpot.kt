package com.tawfiqdev.model

import com.tawfiqdev.enums.SpotType

data class ParkingSpot (
    val id: Long ,
    val parkingId: Long,
    val spotNumber: String,
    val type: SpotType = SpotType.STANDARD,
    val isActive: Boolean = true
)