package com.tawfiqdev.data.repository

import com.tawfiqdev.data.mapper.toDomain
import com.tawfiqdev.data.mapper.toEntity
import com.tawfiqdev.data.room.dao.VehicleDao
import com.tawfiqdev.data.room.entity.VehicleEntity
import com.tawfiqdev.domain.enums.StatusCar
import com.tawfiqdev.domain.model.Vehicle
import com.tawfiqdev.domain.repository.VehicleRepository
import com.tawfiqdev.domain.utils.Error
import com.tawfiqdev.domain.utils.ResultOutput
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

    override fun flowAll(): Flow<List<Vehicle>> = vehicleDao.observeAllCar().map {
        it.map { e ->
            e.toDomain()
        }
    }

    override suspend fun getById(id: Long): ResultOutput<Flow<Vehicle>, Error> =
        try {
            val entity : Flow<VehicleEntity> = vehicleDao.observeCarById(id)
            ResultOutput.Success(entity.map { it.toDomain() })
        } catch (e: Exception) {
            ResultOutput.Failure(Error.Database(e))
        }

    override suspend fun seedIfEmpty() : ResultOutput<Int, Error> =
        try {
            val count = vehicleDao.count()
            if (count == 0) {
                val listVehicle = listOf(
                    VehicleEntity(
                        registrationPlate = "AA-123-BB",
                        marque = "Peugeot",
                        model = "208",
                        siege = 5,
                        statusCar = StatusCar.AVAILABLE
                    ),
                    VehicleEntity(
                        registrationPlate = "CC-456-DD",
                        marque = "Renault",
                        model = "Clio",
                        siege = 5,
                        statusCar = StatusCar.MAINTENANCE
                    ),
                    VehicleEntity(
                        registrationPlate = "EE-789-FF",
                        marque = "Tesla",
                        model = "Model 3",
                        siege = 5,
                        statusCar = StatusCar.AVAILABLE
                    ),
                    VehicleEntity(
                        registrationPlate = "GG-321-HH",
                        marque = "Volkswagen",
                        model = "Golf",
                        siege = 5,
                        statusCar = StatusCar.MAINTENANCE
                    ),
                    VehicleEntity(
                        registrationPlate = "II-654-JJ",
                        marque = "Toyota",
                        model = "Corolla",
                        siege = 5,
                        statusCar = StatusCar.AVAILABLE
                    ),
                    VehicleEntity(
                        registrationPlate = "KK-987-LL",
                        marque = "BMW",
                        model = "X1",
                        siege = 5,
                        statusCar = StatusCar.AVAILABLE
                    ),
                    VehicleEntity(
                        registrationPlate = "MM-246-NN",
                        marque = "Mercedes",
                        model = "Classe A",
                        siege = 5,
                        statusCar = StatusCar.AVAILABLE
                    ),
                    VehicleEntity(
                        registrationPlate = "OO-135-PP",
                        marque = "Audi",
                        model = "A3",
                        siege = 5,
                        statusCar = StatusCar.MAINTENANCE
                    ),
                    VehicleEntity(
                        registrationPlate = "QQ-753-RR",
                        marque = "Ford",
                        model = "Focus",
                        siege = 5,
                        statusCar = StatusCar.AVAILABLE
                    ),
                    VehicleEntity(
                        registrationPlate = "SS-852-TT",
                        marque = "Citroën",
                        model = "C3",
                        siege = 5,
                        statusCar = StatusCar.AVAILABLE
                    )
                )
                vehicleDao.insertAll(listVehicle)
                ResultOutput.Success(listVehicle.size)
            } else {
                ResultOutput.Success(0)
            }
        } catch (e: Exception) {
            ResultOutput.Failure(Error.Database(e))
        }
}