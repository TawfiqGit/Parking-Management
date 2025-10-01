package com.tawfiqdev.model

data class ReservationDetails(
    val reservation: Reservation,
    val user: User,
    val vehicle: Vehicle?,
    val parking: Parking,
    val spot: ParkingSpot?,
    val payments: List<Payment>,
    val entryExitLog: EntryExitLog?
)