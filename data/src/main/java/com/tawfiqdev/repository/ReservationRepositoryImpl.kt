package com.tawfiqdev.repository

import com.tawfiqdev.database.dao.ReservationDao
import com.tawfiqdev.mapper.toDomain
import com.tawfiqdev.mapper.toEntity
import com.tawfiqdev.model.EntryExitLog
import com.tawfiqdev.model.Payment
import com.tawfiqdev.model.Reservation
import com.tawfiqdev.model.ReservationDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/***
 * But = clean architecture (DAO → Service → ViewModel/UI).
 * Ajouter des règles métier (ex: validation, logs, cache) avant d’appeler la DB.
 * **/
class ReservationRepositoryImpl @Inject constructor(
    private val dao: ReservationDao,
    private val io: CoroutineDispatcher
) : ReservationRepository {

    override fun observeReservationDetails(reservationId: Long): Flow<ReservationDetails?> =
        dao.observeReservationFull(reservationId).map {
            it?.toDomain()
        }

    override suspend fun getReservationDetails(reservationId: Long): ReservationDetails? = withContext(io) {
        dao.getReservationFull(reservationId)?.toDomain()
    }

    override fun observeReservationsForUser(userId: Long): Flow<List<ReservationDetails>> =
        dao.observeReservationsForUser(userId).map { list ->
            list.map {
                it.toDomain()
            }
        }

    override suspend fun createReservation(reservation: Reservation): Long = withContext(io) {
        dao.insertReservation(reservation.toEntity())
    }

    override suspend fun updateReservation(reservation: Reservation): Boolean = withContext(io) {
        dao.updateReservation(reservation.toEntity()) > 0
    }

    override suspend fun cancelReservation(reservationId: Long): Boolean = withContext(io) {
        dao.deleteById(reservationId) > 0
    }

    override suspend fun addPayment(payment: Payment): Long = withContext(io) {
        dao.insertPayment(payment.toEntity())
    }

    override suspend fun setEntryExitLog(log: EntryExitLog): Long = withContext(io) {
        dao.insertEntryExitLog(log.toEntity())
    }
}