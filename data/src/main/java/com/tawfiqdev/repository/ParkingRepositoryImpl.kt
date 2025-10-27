package com.tawfiqdev.repository

import com.tawfiqdev.mapper.toDomain
import com.tawfiqdev.model.Parking
import com.tawfiqdev.database.dao.ParkingDao
import com.tawfiqdev.database.entity.ParkingEntity
import com.tawfiqdev.utils.ResultOutput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.tawfiqdev.utils.ErrorState

class ParkingRepositoryImpl @Inject constructor(
    private val parkingDao: ParkingDao
): ParkingRepository {

    override fun observePopular(): Flow<List<Parking>> =
        parkingDao.observeParking().map { it.map { e -> e.toDomain() } }

    override suspend fun seedIfEmpty() : ResultOutput<Int, ErrorState> =
        try {
            val count = parkingDao.count()
            if (count == 0) {
                val seed = listOf(
                    ParkingEntity(name = "ParkEase Pro", pricePerHour = 5.0, rating = 4.9, distanceMins = 5, spots = 28, address = "13 rue auguste renoir 75001 Paris"),
                    ParkingEntity(name = "AutoNest",     pricePerHour = 4.2, rating = 4.8, distanceMins = 10, spots = 10, address = "20 rue soprano 50099 Marseille"),
                    ParkingEntity(name = "CityPark+",    pricePerHour = 6.0, rating = 4.7, distanceMins = 7, spots = 15, address = "54 boulevard anissa 94500 Cretail"),
                )
                parkingDao.insertAll(seed)
                ResultOutput.Success(seed.size)
            } else {
                // rien à insérer
                ResultOutput.Success(0)
            }
        } catch (e: Exception) {
            ResultOutput.Failure(ErrorState.Database(e))
        }
}