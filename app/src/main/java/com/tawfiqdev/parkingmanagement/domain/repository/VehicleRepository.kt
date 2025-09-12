package com.tawfiqdev.parkingmanagement.domain.repository

import com.tawfiqdev.parkingmanagement.domain.model.Vehicle
import com.tawfiqdev.parkingmanagement.domain.utils.ResultOutput
import com.tawfiqdev.parkingmanagement.domain.utils.Error
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    suspend fun insert(vehicle: Vehicle): ResultOutput<Unit, Error>
    suspend fun update(vehicle: Vehicle): ResultOutput<Unit, Error>
    suspend fun delete(vehicle: Vehicle): ResultOutput<Unit, Error>
    suspend fun getAll() : ResultOutput<List<Vehicle>, Error>
    suspend fun getById(id: Long): ResultOutput<Flow<Vehicle>, Error>
    fun flowAll(): Flow<List<Vehicle>>
}
