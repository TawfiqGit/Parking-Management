package com.tawfiqdev.repository

import com.tawfiqdev.mapper.toDomain
import com.tawfiqdev.mapper.toEntity
import com.tawfiqdev.model.Vehicle
import com.tawfiqdev.database.dao.VehicleDao
import com.tawfiqdev.database.entity.VehicleEntity
import com.tawfiqdev.utils.ErrorState
import com.tawfiqdev.utils.ResultOutput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val vehicleDao: VehicleDao,
): VehicleRepository {

    override suspend fun insert(vehicle: Vehicle): ResultOutput<Unit, ErrorState> =
        try {
            vehicleDao.insert(vehicle.toEntity())
            ResultOutput.Success(Unit)
        } catch (e: Exception) {
            ResultOutput.Failure(ErrorState.Database(e))
        }


    override suspend fun update(vehicle: Vehicle): ResultOutput<Unit, ErrorState> =
        try {
            vehicleDao.update(vehicle.toEntity())
            ResultOutput.Success(Unit)
        } catch (e: Exception) {
            ResultOutput.Failure(ErrorState.Database(e))
        }

    override suspend fun delete(vehicle: Vehicle): ResultOutput<Unit, ErrorState> =
        try {
            vehicleDao.delete(vehicle.toEntity())
            ResultOutput.Success(Unit)
        } catch (e: Exception) {
            ResultOutput.Failure(ErrorState.Database(e))
        }

    override suspend fun getAll(): ResultOutput<List<Vehicle>, ErrorState> {
        TODO("Not yet implemented")
    }

    override fun flowAll(): Flow<List<Vehicle>> = vehicleDao.observeAllCar().map { it.map { e -> e.toDomain() } }

    override suspend fun getById(id: Long): ResultOutput<Flow<Vehicle>, ErrorState> =
        try {
            val entity : Flow<VehicleEntity> = vehicleDao.observeCarById(id)
            ResultOutput.Success(entity.map { it.toDomain() })
        } catch (e: Exception) {
            ResultOutput.Failure(ErrorState.Database(e))
        }
}