package com.tawfiqdev.domain.utils

sealed interface Error {
    data class Validation(val reason: String): Error
    data class Database(val cause: Throwable? = null): Error
    data class Unknown(val cause: Throwable? = null): Error
    data object NotFound: Error
}