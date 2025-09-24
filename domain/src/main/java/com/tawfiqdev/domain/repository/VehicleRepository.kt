package com.tawfiqdev.domain.repository

import com.tawfiqdev.domain.model.Vehicle
import com.tawfiqdev.domain.utils.ResultOutput
import com.tawfiqdev.domain.utils.Error
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    suspend fun insert(vehicle: Vehicle): ResultOutput<Unit, Error>
    suspend fun update(vehicle: Vehicle): ResultOutput<Unit, Error>
    suspend fun delete(vehicle: Vehicle): ResultOutput<Unit, Error>
    suspend fun getAll() : ResultOutput<List<Vehicle>, Error>
    suspend fun getById(id: Long): ResultOutput<Flow<Vehicle>, Error>
    suspend fun seedIfEmpty() : ResultOutput<Int, Error>
    fun flowAll(): Flow<List<Vehicle>>
}
