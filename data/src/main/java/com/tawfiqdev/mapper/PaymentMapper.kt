package com.tawfiqdev.mapper

import com.tawfiqdev.database.entity.PaymentEntity
import com.tawfiqdev.model.Payment

fun PaymentEntity.toDomain() = Payment(
    id = id,
    providerRef = providerRef,
    reservationId = reservationId,
    createdAt = createdAt,
    amount = amount,
    method = method,
    status = status,
    currency = currency
)

fun Payment.toEntity() = PaymentEntity(
    id = id,
    providerRef = providerRef,
    reservationId = reservationId,
    createdAt = createdAt,
    amount = amount,
    method = method,
    status = status,
    currency = currency
)
