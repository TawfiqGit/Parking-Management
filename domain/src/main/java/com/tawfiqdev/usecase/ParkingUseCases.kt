package com.tawfiqdev.usecase

import com.tawfiqdev.model.Parking
import com.tawfiqdev.repository.ParkingRepository
import com.tawfiqdev.utils.ErrorState
import com.tawfiqdev.utils.ResultOutput
import kotlinx.coroutines.flow.Flow

class ObservePopularParkingUseCase(private val repo: ParkingRepository) {
    operator fun invoke() : Flow<List<Parking>> = repo.observePopular()
}

class SeedParkingIfEmptyUseCase(private val repo: ParkingRepository) {
    suspend operator fun invoke() : ResultOutput<Int, ErrorState> = repo.seedIfEmpty()
}
