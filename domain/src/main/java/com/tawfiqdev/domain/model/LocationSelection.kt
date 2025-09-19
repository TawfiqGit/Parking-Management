package com.tawfiqdev.domain.model

data class LocationSelection(
    val id: Int,
    val title: String,
    val address: String,
    val latitude: Double?,
    val longitude: Double?
)