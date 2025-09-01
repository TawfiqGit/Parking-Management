package com.tawfiqdev.parkingmanagement.domain.utils

sealed interface VehicleError {
    data class Validation(val reason: String): VehicleError
    data class Database(val cause: Throwable? = null): VehicleError
    data class Unknown(val cause: Throwable? = null): VehicleError
    data object NotFound: VehicleError
}