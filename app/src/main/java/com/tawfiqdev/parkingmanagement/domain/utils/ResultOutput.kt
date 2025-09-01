package com.tawfiqdev.parkingmanagement.domain.utils

sealed class ResultOutput<out T, out E> {
    data class Success<T>(val value: T): ResultOutput<T, Nothing>()
    data class Failure<E>(val error: E): ResultOutput<Nothing, E>()
}

inline fun <T, E> ResultOutput<T, E>.getOrNull(): T? = (this as? ResultOutput.Success)?.value

inline fun <T, E> ResultOutput<T, E>.errorOrNull(): E? = (this as? ResultOutput.Failure)?.error