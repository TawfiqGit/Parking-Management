package com.tawfiqdev.parkingmanagement.domain.usecase

import com.tawfiqdev.parkingmanagement.domain.model.LocationSelection
import com.tawfiqdev.parkingmanagement.domain.repository.LocationRepository

class ObserveRecentLocationsUseCase(private val repo: LocationRepository) {
    operator fun invoke() = repo.observeRecent()
}

class SaveLocationUseCase(private val repo: LocationRepository) {
    suspend operator fun invoke(selection: LocationSelection) = repo.save(selection)
}