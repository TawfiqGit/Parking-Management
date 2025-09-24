package com.tawfiqdev.usecase

import com.tawfiqdev.model.Vehicle
import com.tawfiqdev.repository.VehicleRepository
import com.tawfiqdev.utils.ErrorState
import com.tawfiqdev.utils.ResultOutput
import kotlinx.coroutines.flow.Flow

class FlowAllVehicleUseCase (private val repository: VehicleRepository){
    operator fun invoke(): Flow<List<Vehicle>> = repository.flowAll()
}

class GetVehicleByIdUseCase(private val repository: VehicleRepository) {
    suspend operator fun invoke(id: Long) = repository.getById(id)
}

class InsertVehicleUseCase (private val repository: VehicleRepository) {
    suspend operator fun invoke(input: Vehicle): ResultOutput<Unit, ErrorState>  {
        if (input.registrationPlate.isBlank()){
            return ResultOutput.Failure(ErrorState.Validation("Plaque vide"))
        }
        if (input.model.isBlank()){
            return ResultOutput.Failure(ErrorState.Validation("Marque vide"))
        }
        if (input.model.isBlank()){
            return ResultOutput.Failure(ErrorState.Validation("Modèle vide"))
        }
        if (input.siege <= 0){
            return ResultOutput.Failure(ErrorState.Validation("Nombre de places invalide"))
        }
        return repository.insert(input.copy(id = 0))
    }
}

class UpdateVehicleUseCase (private val repository: VehicleRepository) {
    suspend operator fun invoke(vehicle: Vehicle): ResultOutput<Unit, ErrorState> {
        if (vehicle.id <= 0) {
            return ResultOutput.Failure(ErrorState.Validation("Id invalide"))
        }
        if (vehicle.registrationPlate.isBlank()){
            return ResultOutput.Failure(ErrorState.Validation("Plaque vide"))
        }
        return repository.update(vehicle)
    }
}