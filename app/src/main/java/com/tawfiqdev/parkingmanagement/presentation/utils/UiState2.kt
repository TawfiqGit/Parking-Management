package com.tawfiqdev.parkingmanagement.presentation.utils

sealed interface UiState2<out T> {
    data object Loading : UiState2<Nothing>
    data object Empty : UiState2<Nothing>
    data class Success<T>(
        val items: T
    ) : UiState2<T>

    data class Error(
        val message: String
    ) : UiState2<Nothing>
}