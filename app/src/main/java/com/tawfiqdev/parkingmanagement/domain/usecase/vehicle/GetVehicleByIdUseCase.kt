package com.tawfiqdev.parkingmanagement.domain.usecase.vehicle

import com.tawfiqdev.parkingmanagement.domain.repository.VehicleRepository
import javax.inject.Inject

class GetVehicleByIdUseCase @Inject constructor(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(id: Long) = repository.getById(id)
}