package com.tawfiqdev.parkingmanagement.domain.repository

import com.tawfiqdev.parkingmanagement.domain.model.Vehicle
import com.tawfiqdev.parkingmanagement.domain.utils.ResultOutput
import com.tawfiqdev.parkingmanagement.domain.utils.VehicleError
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    suspend fun insert(vehicle: Vehicle): ResultOutput<Unit, VehicleError>
    suspend fun update(vehicle: Vehicle): ResultOutput<Unit, VehicleError>

    suspend fun delete(vehicle: Vehicle): ResultOutput<Unit, VehicleError>

    suspend fun getAll() : ResultOutput<List<Vehicle>, VehicleError>
    suspend fun getById(id: Long): ResultOutput<Flow<Vehicle>, VehicleError>
    fun flowAll(): Flow<List<Vehicle>>
}
