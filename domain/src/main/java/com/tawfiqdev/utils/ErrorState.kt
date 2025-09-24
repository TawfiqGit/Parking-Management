package com.tawfiqdev.utils

sealed interface ErrorState {
    data class Validation(val reason: String): ErrorState
    data class Database(val cause: Throwable? = null): ErrorState
    data class Unknown(val cause: Throwable? = null): ErrorState
    data object NotFound: ErrorState
}