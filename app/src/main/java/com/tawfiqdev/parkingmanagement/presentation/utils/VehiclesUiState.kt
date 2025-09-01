package com.tawfiqdev.parkingmanagement.presentation.utils

import com.tawfiqdev.parkingmanagement.domain.model.Vehicle

sealed interface VehiclesUiState {
    data object Loading : VehiclesUiState
    data class Success(val items: List<Vehicle>) : VehiclesUiState
    data object Empty : VehiclesUiState
    data class Error(val message: String) : VehiclesUiState
}