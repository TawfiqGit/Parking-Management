package com.tawfiqdev.parkingmanagement.domain.usecase

import com.tawfiqdev.parkingmanagement.domain.repository.ParkingRepository

class ObservePopularParkingUseCase(private val repo: ParkingRepository) {
    operator fun invoke() = repo.observePopular()
}

class GetParkingByIdUseCase(private val repo: ParkingRepository) {
    suspend operator fun invoke(id: Int) = repo.getParkingById(id)
}

class SeedParkingIfEmptyUseCase(private val repo: ParkingRepository) {
    suspend operator fun invoke() = repo.seedIfEmpty()
}