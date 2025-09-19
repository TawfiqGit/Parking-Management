package com.tawfiqdev.data.repository

import com.tawfiqdev.data.mapper.toDomain
import com.tawfiqdev.data.mapper.toEntity
import com.tawfiqdev.data.room.dao.VehicleDao
import com.tawfiqdev.data.room.entity.VehicleEntity
import com.tawfiqdev.domain.model.Vehicle
import com.tawfiqdev.domain.repository.VehicleRepository
import com.tawfiqdev.domain.utils.ResultOutput
import com.tawfiqdev.domain.utils.Error
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val vehicleDao: VehicleDao,
): VehicleRepository {

    override suspend fun insert(vehicle: Vehicle): ResultOutput<Unit, Error> =
        try {
            vehicleDao.insert(vehicle.toEntity())
            ResultOutput.Success(Unit)
        } catch (e: Exception) {
            ResultOutput.Failure(Error.Database(e))
        }


    override suspend fun update(vehicle: Vehicle): ResultOutput<Unit, Error> =
        try {
            vehicleDao.update(vehicle.toEntity())
            ResultOutput.Success(Unit)
        } catch (e: Exception) {
            ResultOutput.Failure(Error.Database(e))
        }

    override suspend fun delete(vehicle: Vehicle): ResultOutput<Unit, Error> =
        try {
            vehicleDao.delete(vehicle.toEntity())
            ResultOutput.Success(Unit)
        } catch (e: Exception) {
            ResultOutput.Failure(Error.Database(e))
        }

    override suspend fun getAll(): ResultOutput<List<Vehicle>, Error> {
        TODO("Not yet implemented")
    }

    override fun flowAll(): Flow<List<Vehicle>> = vehicleDao.observeAllCar().map { it.map { e -> e.toDomain() } }

    override suspend fun getById(id: Long): ResultOutput<Flow<Vehicle>, Error> =
        try {
            val entity : Flow<VehicleEntity> = vehicleDao.observeCarById(id)
            ResultOutput.Success(entity.map { it.toDomain() })
        } catch (e: Exception) {
            ResultOutput.Failure(Error.Database(e))
        }
}