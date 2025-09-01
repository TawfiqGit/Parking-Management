package com.tawfiqdev.parkingmanagement.domain.usecase

import com.tawfiqdev.parkingmanagement.domain.model.Vehicle
import com.tawfiqdev.parkingmanagement.domain.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlowAllVehicleUseCase @Inject constructor(
    private val repository: VehicleRepository
){
    operator fun invoke(): Flow<List<Vehicle>> = repository.flowAll()
}