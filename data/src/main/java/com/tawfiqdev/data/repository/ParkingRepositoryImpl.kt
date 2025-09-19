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
                        pricePerHour = 5.0,
                        rating = 4.9,
                        distanceMins = 5,
                        spots = 28
                    ),
                    ParkingEntity(name = "AutoNest",     pricePerHour = 4.2, rating = 4.8, distanceMins = 10, spots = 10),
                    ParkingEntity(name = "CityPark+",    pricePerHour = 6.0, rating = 4.7, distanceMins = 7, spots = 15)
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