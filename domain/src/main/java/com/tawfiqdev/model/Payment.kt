package com.tawfiqdev.model

import com.tawfiqdev.enums.PaymentStatus

data class Payment (
    val id: Long ,
    val reservationId: Long,
    val providerRef: String?,
    val createdAt: Long,
    val amount: Double,
    val currency: String = "EUR",
    val status: PaymentStatus,
    val method: String,
)