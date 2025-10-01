package com.tawfiqdev.repository

import com.tawfiqdev.model.EntryExitLog
import com.tawfiqdev.model.Parking
import com.tawfiqdev.model.Payment
import com.tawfiqdev.model.Reservation
import com.tawfiqdev.model.ReservationDetails
import com.tawfiqdev.utils.ErrorState
import com.tawfiqdev.utils.ResultOutput
import kotlinx.coroutines.flow.Flow

interface ReservationRepository {
    fun observeReservationDetails(reservationId: Long): Flow<ReservationDetails?>
    fun observeReservationsForUser(userId: Long): Flow<List<ReservationDetails>>
    suspend fun getReservationDetails(reservationId: Long): ReservationDetails?

    suspend fun createReservation(reservation: Reservation): Long
    suspend fun updateReservation(reservation: Reservation): Boolean
    suspend fun cancelReservation(reservationId: Long): Boolean

    // Paiements / logs
    suspend fun addPayment(payment: Payment): Long
    suspend fun setEntryExitLog(log: EntryExitLog): Long
}
