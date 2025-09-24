package com.tawfiqdev.parkingmanagement.presentation.utils

sealed interface UiState {
    data object Idle : UiState
    data object Loading : UiState
    data object Empty : UiState
    data class Success <T> (val items: T) : UiState
    data class Error(val message: String) : UiState
}