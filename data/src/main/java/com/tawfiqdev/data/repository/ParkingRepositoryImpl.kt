package com.tawfiqdev.data.repository

import com.tawfiqdev.data.mapper.toDomain
import com.tawfiqdev.data.room.dao.ParkingDao
import com.tawfiqdev.data.room.entity.ParkingEntity
import com.tawfiqdev.domain.model.Parking
import com.tawfiqdev.domain.repository.ParkingRepository
import com.tawfiqdev.domain.utils.ResultOutput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.map
import com.tawfiqdev.domain.utils.Error

class ParkingRepositoryImpl @Inject constructor(
    private val parkingDao: ParkingDao
): ParkingRepository {

    override suspend fun observePopular(): Flow<List<Parking>> =
        parkingDao.observeParking().map { it.map { e -> e.toDomain() } }

    override suspend fun seedIfEmpty() : ResultOutput<Int, Error> =
        try {
            val count = parkingDao.count()
            if (count == 0) {
                val seed = listOf(
                    ParkingEntity(
                        name = "ParkEase Pro",
                        address = "10 Rue de la Paix",
                        city = "Paris",
                        country = "FR",
                        latitude = 48.8698,
                        longitude = 2.3320
                    ),
                    ParkingEntity(
                        name = "AutoNest",
                        address = "5 Avenue des Champs-Élysées",
                        city = "Paris",
                        country = "FR",
                        latitude = 48.8695,
                        longitude = 2.3073
                    ),
                    ParkingEntity(
                        name = "CityPark+",
                        address = "3 Boulevard Haussmann",
                        city = "Paris",
                        country = "FR",
                        latitude = 48.8720,
                        longitude = 2.3325
                    )
                )
                parkingDao.insertAll(seed)
                ResultOutput.Success(seed.size)
            } else {
                // rien à insérer
                ResultOutput.Success(0)
            }
        } catch (e: Exception) {
            ResultOutput.Failure(Error.Database(e))
        }
}