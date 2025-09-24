package com.tawfiqdev.domain.repository

import com.tawfiqdev.domain.model.Reservation
import com.tawfiqdev.domain.model.ReservationSummary
import kotlinx.coroutines.flow.Flow

/**
 * Abstraction du DAO
 * Permet de découpler le code métier de Room directement
 * suspend pour eviter le main thread
 */
interface ReservationRepository {

    suspend fun createReservationSafe(
        vehicleId: Long,
        parkingId: Long,
        spotId: Long?,
        start: Long,
        end: Long,
        expectedPriceCents: Long?
    ): Long

    suspend fun getReservationSummary(reservationId: Long): ReservationSummary

    suspend fun assignSpot(reservationId: Long, spotId: Long)

    suspend fun releaseSpot(reservationId: Long)

    suspend fun cancel(reservationId: Long)

    suspend fun activate(reservationId: Long)

    suspend fun complete(reservationId: Long)

    fun observeReservation(): Flow<List<Reservation>>

    suspend fun isSpotAvailable(spotId: Long, start: String, end: String): Boolean
}