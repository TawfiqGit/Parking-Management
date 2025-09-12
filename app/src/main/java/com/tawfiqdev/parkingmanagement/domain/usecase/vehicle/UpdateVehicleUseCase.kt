package com.tawfiqdev.parkingmanagement.domain.usecase.vehicle

import com.tawfiqdev.parkingmanagement.domain.model.Vehicle
import com.tawfiqdev.parkingmanagement.domain.utils.Error
import com.tawfiqdev.parkingmanagement.domain.repository.VehicleRepository
import com.tawfiqdev.parkingmanagement.domain.utils.ResultOutput
import javax.inject.Inject

class UpdateVehicleUseCase @Inject constructor(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(vehicle: Vehicle): ResultOutput<Unit, Error> {
        if (vehicle.id <= 0) {
            return ResultOutput.Failure(Error.Validation("Id invalide"))
        }
        if (vehicle.registrationPlate.isBlank()){
            return ResultOutput.Failure(Error.Validation("Plaque vide"))
        }
        return repository.update(vehicle)
    }
}