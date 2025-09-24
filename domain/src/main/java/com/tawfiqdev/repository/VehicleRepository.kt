package com.tawfiqdev.repository

import com.tawfiqdev.model.Vehicle
import com.tawfiqdev.utils.ResultOutput
import com.tawfiqdev.utils.ErrorState
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    suspend fun insert(vehicle: Vehicle): ResultOutput<Unit, ErrorState>
    suspend fun update(vehicle: Vehicle): ResultOutput<Unit, ErrorState>
    suspend fun delete(vehicle: Vehicle): ResultOutput<Unit, ErrorState>
    suspend fun getAll() : ResultOutput<List<Vehicle>, ErrorState>
    suspend fun getById(id: Long): ResultOutput<Flow<Vehicle>, ErrorState>
    fun flowAll(): Flow<List<Vehicle>>
}
