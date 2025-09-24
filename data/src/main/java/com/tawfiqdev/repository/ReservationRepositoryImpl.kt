package com.tawfiqdev.repository

import com.tawfiqdev.enums.StatusRez
import com.tawfiqdev.room.db.ParkingMgmtDatabase
import com.tawfiqdev.room.entity.ReservationEntity
import com.tawfiqdev.room.entity.VehicleEntity
import kotlinx.coroutines.flow.Flow
import java.time.Instant
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

    override suspend fun createReservationSafe(
        vehicleId: Long,
        userId: Long,
        start: Instant,
        end: Instant,
        notes: String?
    ): Long {
        val reservation = ReservationEntity(
            vehicleId = vehicleId,
            userId = userId,
            startTime = start,
            endTime = end,
            statusRes = StatusRez.CONFIRMED,
            notes = notes
        )
        return database.reservationDao().insert(reservation)
    }
}