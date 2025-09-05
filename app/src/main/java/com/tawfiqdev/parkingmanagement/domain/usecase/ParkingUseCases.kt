package com.tawfiqdev.parkingmanagement.domain.usecase

import com.tawfiqdev.parkingmanagement.domain.model.Parking
import com.tawfiqdev.parkingmanagement.domain.repository.ParkingRepository
import com.tawfiqdev.parkingmanagement.domain.utils.Error
import com.tawfiqdev.parkingmanagement.domain.utils.ResultOutput
import kotlinx.coroutines.flow.Flow

class ObservePopularParkingUseCase(private val repo: ParkingRepository) {
    operator fun invoke() : Flow<List<Parking>> = repo.observePopular()
}

class SeedParkingIfEmptyUseCase(private val repo: ParkingRepository) {
    suspend operator fun invoke() : ResultOutput<Int, Error> = repo.seedIfEmpty()
}