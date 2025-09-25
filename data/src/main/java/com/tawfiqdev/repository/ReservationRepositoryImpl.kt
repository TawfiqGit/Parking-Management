package com.tawfiqdev.repository

import com.tawfiqdev.database.ParkingMgmtDatabase
import com.tawfiqdev.database.entity.VehicleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/***
 * But = clean architecture (DAO → Service → ViewModel/UI).
 * Ajouter des règles métier (ex: validation, logs, cache) avant d’appeler la DB.
 * **/
class ReservationRepositoryImpl @Inject constructor(
    private val database: ParkingMgmtDatabase
) : ReservationRepository {

    override fun allVehicles(): Flow<List<VehicleEntity>> = database.vehicleDao().observeAllCar()

    override suspend fun addVehicle(vehicle: VehicleEntity): Long = database.vehicleDao().insert(vehicle)
}