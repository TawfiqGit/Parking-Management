package com.tawfiqdev.mapper

import com.tawfiqdev.database.entity.EntryExitLogEntity
import com.tawfiqdev.model.EntryExitLog

fun EntryExitLogEntity.toDomain() = EntryExitLog(
    id = id,
    reservationId = reservationId,
    entryTime = entryTime,
    exitTime = exitTime,
    gateName = gateName
)

fun EntryExitLog.toEntity() = EntryExitLogEntity(
    id = id,
    reservationId = reservationId,
    entryTime = entryTime,
    exitTime = exitTime,
    gateName = gateName
)