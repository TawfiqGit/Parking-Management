package com.tawfiqdev.usecase

import com.tawfiqdev.model.EntryExitLog
import com.tawfiqdev.model.Parking
import com.tawfiqdev.model.Payment
import com.tawfiqdev.model.Reservation
import com.tawfiqdev.model.ReservationDetails
import com.tawfiqdev.repository.ReservationRepository
import com.tawfiqdev.utils.ErrorState
import com.tawfiqdev.utils.ResultOutput
import kotlinx.coroutines.flow.Flow

class ObserveReservationDetailsUseCase(private val repo: ReservationRepository) {
    operator fun invoke(reservationId: Long): Flow<ReservationDetails?> =
        repo.observeReservationDetails(reservationId)
}

class ObserveUserReservationsUseCase(private val repo: ReservationRepository) {
    operator fun invoke(userId: Long): Flow<List<ReservationDetails>> =
        repo.observeReservationsForUser(userId)
}

class GetReservationDetailsUseCase(private val repo: ReservationRepository) {
    suspend operator fun invoke(reservationId: Long): ReservationDetails? =
        repo.getReservationDetails(reservationId)
}

class CreateOrUpdateReservationUseCase(private val repo: ReservationRepository) {
    suspend operator fun invoke(reservation: Reservation): Long {
        return if (reservation.id == 0L) {
            repo.createReservation(reservation)
        } else {
            repo.updateReservation(reservation)
            reservation.id
        }
    }
}

class CancelReservationUseCase(private val repo: ReservationRepository) {
    suspend operator fun invoke(reservationId: Long): Boolean =
        repo.cancelReservation(reservationId)
}

class AddPaymentUseCase(private val repo: ReservationRepository) {
    suspend operator fun invoke(payment: Payment): Long = repo.addPayment(payment)
}

class SetEntryExitLogUseCase(private val repo: ReservationRepository) {
    suspend operator fun invoke(log: EntryExitLog): Long =
        repo.setEntryExitLog(log)
}