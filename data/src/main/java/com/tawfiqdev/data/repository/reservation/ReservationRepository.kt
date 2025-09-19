package com.tawfiqdev.data.repository.reservation

import com.tawfiqdev.data.room.entity.VehicleEntity
import kotlinx.coroutines.flow.Flow
import java.time.Instant

/**
 * Abstraction du DAO
 * Permet de découpler le code métier de Room directement
 * suspend pour eviter le main thread
 */
interface ReservationRepository {

    fun allVehicles(): Flow<List<VehicleEntity>>

    suspend fun addVehicle(vehicle: VehicleEntity): Long

    suspend fun createReservationSafe(
        vehicleId: Long,
        userId: Long,
        parkingId: Long,
        spotId: Long?,
        start: Instant,
        end: Instant,
        expectedPriceCents: Long? = null
    ): Long
}