package com.tawfiqdev.domain.usecase

import com.tawfiqdev.domain.repository.ParkingRepository

/**
 * Puriste “Clean Architecture 100%” (zéro dépendance externe dans domain)
 * Utlisatio data/di/UseCaseModule
 */
class ObservePopularParkingUseCase (private val parkingRepository: ParkingRepository) {
    suspend operator fun invoke() = parkingRepository.observePopular()
}

class SeedParkingIfEmptyUseCase(private val parkingRepository: ParkingRepository) {
    suspend operator fun invoke() = parkingRepository.seedIfEmpty()
}