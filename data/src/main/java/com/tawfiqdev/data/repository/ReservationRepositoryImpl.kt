package com.tawfiqdev.data.repository

import com.tawfiqdev.data.mapper.toDomain
import com.tawfiqdev.data.room.dao.ReservationDao
import com.tawfiqdev.data.room.entity.ReservationEntity
import com.tawfiqdev.domain.enums.ReservationStatus
import com.tawfiqdev.domain.model.Reservation
import com.tawfiqdev.domain.model.ReservationSummary
import com.tawfiqdev.domain.repository.ReservationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import javax.inject.Inject

/***
 * But = clean architecture (DAO → Service → ViewModel/UI).
 * Ajouter des règles métier (ex: validation, logs, cache) avant d’appeler la DB.
 * **/
class ReservationRepositoryImpl @Inject constructor(
    private val reservationDao: ReservationDao,
) : ReservationRepository {

    override suspend fun createReservationSafe(
        vehicleId: Long,
        parkingId: Long,
        spotId: Long?,
        start: Long,
        end: Long,
        expectedPriceCents: Long?
    ): Long {
        if (spotId != null) {
            val conflicts = reservationDao.countConflicts(
                spotId = spotId,
                startsAt = Instant.now().toEpochMilli(),
                endsAt = Instant.now().toEpochMilli(),
            )
            if (conflicts > 0) error("Reservation conflict on spot #$spotId")
        }

        val entity = ReservationEntity(
            vehicleId = vehicleId,
            parkingId = parkingId,
            spotId = spotId,
            startsAt = start,
            endsAt = end,
            expectedPriceCents = expectedPriceCents,
            createdAt = System.currentTimeMillis()
        )
        return reservationDao.insert(entity)
    }


    override suspend fun getReservationSummary(reservationId: Long): ReservationSummary {
        val details = reservationDao.getReservationWithDetails(reservationId)

        val vehicleLabel = "${details.vehicle.marque} ${details.vehicle.model} • ${details.vehicle.registrationPlate}"
        val spotLabel = details.spot?.let { "Place ${it.spotCode}" }

        val fmt = java.time.format.DateTimeFormatter.ofPattern("HH:mm")
            .withZone(java.time.ZoneId.systemDefault())
        val timeRange = "${fmt.format(java.time.Instant.ofEpochMilli(details.reservation.startsAt))} → " +
                fmt.format(java.time.Instant.ofEpochMilli(details.reservation.endsAt))

        val expectedPrice = details.reservation.expectedPriceCents?.let { "€" + String.format("%.2f", it / 100.0) }

        return ReservationSummary(
            reservationId = details.reservation.id,
            status = ReservationStatus.valueOf(details.reservation.status.name),
            vehicleLabel = vehicleLabel,
            parkingName = details.parking.name,
            parkingAddress = details.parking.address,
            spotLabel = spotLabel,
            timeRange = timeRange,
            expectedPrice = expectedPrice
        )
    }

    override suspend fun assignSpot(reservationId: Long, spotId: Long) {
        val r = reservationDao.getById(reservationId) ?: error("Reservation not found")
        val conflicts = reservationDao.countConflicts(
            spotId = spotId,
            startsAt = r.startsAt,
            endsAt = r.endsAt
        )
        if (conflicts > 0) {
            error("Reservation conflict on spot #$spotId")
        }
        reservationDao.setSpot(reservationId, spotId)
    }

    override suspend fun releaseSpot(reservationId: Long) {
        reservationDao.setSpot(reservationId, null)
    }

    override suspend fun cancel(reservationId: Long) {
        reservationDao.setStatus(reservationId, ReservationStatus.CANCELLED.name)
    }

    override suspend fun activate(reservationId: Long) {
        reservationDao.setStatus(reservationId, ReservationStatus.ACTIVE.name)
    }

    override suspend fun complete(reservationId: Long) {
        reservationDao.setStatus(reservationId, ReservationStatus.COMPLETED.name)
    }

    override fun observeReservation(): Flow<List<Reservation>> =
        reservationDao.observeByUser().map {
            it.map { e ->
                e.toDomain()
            }
        }

    override suspend fun isSpotAvailable(
        spotId: Long,
        start: String,
        end: String
    ): Boolean {
        val conflicts = reservationDao.countConflicts(
            spotId = spotId,
            startsAt = Instant.now().toEpochMilli(),
            endsAt = Instant.now().toEpochMilli()
        )
        return conflicts == 0
    }
}