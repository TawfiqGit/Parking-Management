package com.tawfiqdev.repository

import com.tawfiqdev.database.entity.VehicleEntity
import kotlinx.coroutines.flow.Flow

/**
 * Abstraction du DAO
 * Permet de découpler le code métier de Room directement
 * suspend pour eviter le main thread
 */
interface ReservationRepository {

    fun allVehicles(): Flow<List<VehicleEntity>>

    suspend fun addVehicle(vehicle: VehicleEntity): Long
}