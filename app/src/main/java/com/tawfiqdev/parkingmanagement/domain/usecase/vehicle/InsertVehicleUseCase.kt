package com.tawfiqdev.parkingmanagement.domain.usecase.vehicle

import com.tawfiqdev.parkingmanagement.domain.model.Vehicle
import com.tawfiqdev.parkingmanagement.domain.utils.Error
import com.tawfiqdev.parkingmanagement.domain.repository.VehicleRepository
import com.tawfiqdev.parkingmanagement.domain.utils.ResultOutput
import javax.inject.Inject

class InsertVehicleUseCase @Inject constructor(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(input: Vehicle): ResultOutput<Unit, Error>  {
        if (input.registrationPlate.isBlank()){
            return ResultOutput.Failure(Error.Validation("Plaque vide"))
        }
        if (input.model.isBlank()){
            return ResultOutput.Failure(Error.Validation("Marque vide"))
        }
        if (input.model.isBlank()){
            return ResultOutput.Failure(Error.Validation("Modèle vide"))
        }
        if (input.siege <= 0){
            return ResultOutput.Failure(Error.Validation("Nombre de places invalide"))
        }
        return repository.insert(input.copy(id = 0))
    }
}