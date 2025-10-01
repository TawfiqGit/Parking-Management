package com.tawfiqdev.model

data class EntryExitLog (
    val id: Long ,
    val reservationId: Long,
    val entryTime: Long?,
    val exitTime: Long?,
    val gateName: String?
)