package com.tawfiqdev.data.repository.reservation

import com.tawfiqdev.data.room.ParkingMgmtDatabase
import com.tawfiqdev.data.room.entity.ReservationEntity
import com.tawfiqdev.data.room.entity.VehicleEntity
import com.tawfiqdev.domain.enums.ReservationStatus
import kotlinx.coroutines.flow.Flow
import java.time.Instant
import javax.inject.Inject

/***
 * But = clean architecture (DAO → Service → ViewModel/UI).
 * Ajouter des règles métier (ex: validation, logs, cache) avant d’appeler la DB.
 * **/
class ReservationRepositoryImpl @Inject constructor(
    private val database: ParkingMgmtDatabase
) : ReservationRepository{

    override fun allVehicles(): Flow<List<VehicleEntity>> =
        database.vehicleDao().observeAllCar()

    override suspend fun addVehicle(vehicle: VehicleEntity): Long =
        database.vehicleDao().insert(vehicle)

    override suspend fun createReservationSafe(
        vehicleId: Long,
        userId: Long,
        parkingId: Long,
        spotId: Long?,
        start: Instant,
        end: Instant,
        expectedPriceCents: Long?
    ): Long {
        if (spotId != null) {
            val conflicts = database.reservationDao().countConflicts(
                spotId = spotId,
                startsAt = start.toEpochMilli(),
                endsAt = end.toEpochMilli()
            )
            if (conflicts > 0) {
                throw IllegalStateException("Reservation conflict on spot #$spotId")
            }
        }

        val entity = ReservationEntity(
            userId = userId,
            vehicleId = vehicleId,
            parkingId = parkingId,
            spotId = spotId,
            startsAt = start.toEpochMilli(),
            endsAt = end.toEpochMilli(),
            status = ReservationStatus.BOOKED,
            expectedPriceCents = expectedPriceCents,
            createdAt = System.currentTimeMillis()
        )
        return database.reservationDao().insert(entity)

    }
}