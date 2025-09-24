package com.tawfiqdev.domain.usecase

import com.tawfiqdev.domain.model.ReservationSummary
import com.tawfiqdev.domain.repository.ReservationRepository

class CreateReservationUseCase(private val repository: ReservationRepository) {
    data class Params(
        val vehicleId: Long,
        val parkingId: Long,
        val spotId: Long?,
        val start: Long,
        val end: Long,
        val expectedPriceCents: Long?
    )

    suspend operator fun invoke(params: Params): Long {
        return repository.createReservationSafe(
            vehicleId = params.vehicleId,
            parkingId = params.parkingId,
            spotId = params.spotId,
            start = params.start,
            end = params.end,
            expectedPriceCents = params.expectedPriceCents
        )
    }
}

class GetReservationSummaryUseCase (private val repository: ReservationRepository) {
    suspend operator fun invoke(reservationId: Long): ReservationSummary {
        return repository.getReservationSummary(reservationId)
    }
}

class CreateReservationAndGetSummaryUseCase (
    private val createReservation: CreateReservationUseCase,
    private val getSummary: GetReservationSummaryUseCase
) {
    data class Params(
        val vehicleId: Long,
        val parkingId: Long,
        val spotId: Long?,
        val start: Long,
        val end: Long,
        val expectedPriceCents: Long?
    )

    suspend operator fun invoke(params: Params): ReservationSummary {
        val id = createReservation(
            CreateReservationUseCase.Params(
                vehicleId = params.vehicleId,
                parkingId = params.parkingId,
                spotId = params.spotId,
                start = params.start,
                end = params.end,
                expectedPriceCents = params.expectedPriceCents
            )
        )
        return getSummary(id)
    }
}

class AssignSpotUseCase (private val reservationRepository: ReservationRepository) {
    suspend operator fun invoke(reservationId: Long, spotId: Long) = reservationRepository.assignSpot(reservationId, spotId)
}

class ReleaseSpotUseCase (private val reservationRepository: ReservationRepository) {
    suspend operator fun invoke(reservationId: Long) = reservationRepository.releaseSpot(reservationId)
}

class CancelReservationUseCase (private val reservationRepository: ReservationRepository) {
    suspend operator fun invoke(reservationId: Long) = reservationRepository.cancel(reservationId)
}

class CompleteReservationUseCase (private val reservationRepository: ReservationRepository) {
    suspend operator fun invoke(reservationId: Long) = reservationRepository.complete(reservationId)
}

class ActivateReservationUseCase (private val reservationRepository: ReservationRepository) {
    suspend operator fun invoke(reservationId: Long) = reservationRepository.activate(reservationId)
}

class IsSpotAvailableUseCase (private val reservationRepository: ReservationRepository) {
    suspend operator fun invoke(spotId: Long, start: String, end: String) = reservationRepository.isSpotAvailable(spotId, start, end)
}

class ObserveReservationsUseCase (private val reservationRepository: ReservationRepository) {
    operator fun invoke() = reservationRepository.observeReservation()
}
