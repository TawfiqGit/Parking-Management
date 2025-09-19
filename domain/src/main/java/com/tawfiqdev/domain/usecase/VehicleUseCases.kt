package com.tawfiqdev.domain.usecase

import com.tawfiqdev.domain.model.Vehicle
import com.tawfiqdev.domain.repository.VehicleRepository
import com.tawfiqdev.domain.utils.Error
import com.tawfiqdev.domain.utils.ResultOutput
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlowAllVehicleUseCase (private val repository: VehicleRepository){
    operator fun invoke(): Flow<List<Vehicle>> = repository.flowAll()
}

class GetVehicleByIdUseCase(private val repository: VehicleRepository) {
    suspend operator fun invoke(id: Long) = repository.getById(id)
}

class InsertVehicleUseCase (private val repository: VehicleRepository) {
    suspend operator fun invoke(input: Vehicle): ResultOutput<Unit, com.tawfiqdev.domain.utils.Error> {
        if (input.registrationPlate.isBlank()){
            return ResultOutput.Failure(com.tawfiqdev.domain.utils.Error.Validation("Plaque vide"))
        }
        if (input.model.isBlank()){
            return ResultOutput.Failure(com.tawfiqdev.domain.utils.Error.Validation("Marque vide"))
        }
        if (input.model.isBlank()){
            return ResultOutput.Failure(com.tawfiqdev.domain.utils.Error.Validation("Modèle vide"))
        }
        if (input.siege <= 0){
            return ResultOutput.Failure(Error.Validation("Nombre de places invalide"))
        }
        return repository.insert(input.copy(id = 0))
    }
}

class UpdateVehicleUseCase (private val repository: VehicleRepository) {
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