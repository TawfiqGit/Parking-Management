package com.tawfiqdev.parkingmanagement.data.repository

import com.tawfiqdev.parkingmanagement.data.mapper.toDomain
import com.tawfiqdev.parkingmanagement.data.room.dao.ParkingDao
import com.tawfiqdev.parkingmanagement.data.room.entity.ParkingEntity
import com.tawfiqdev.parkingmanagement.domain.model.Parking
import com.tawfiqdev.parkingmanagement.domain.repository.ParkingRepository
import com.tawfiqdev.parkingmanagement.domain.utils.Error
import com.tawfiqdev.parkingmanagement.domain.utils.ResultOutput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ParkingRepositoryImpl @Inject constructor(
    private val parkingDao: ParkingDao
): ParkingRepository {

    override fun observePopular(): ResultOutput<Flow<List<Parking>>, Error> =
        try {
            val entity =  parkingDao.observeParking()
            ResultOutput.Success(value = entity.map {
                it.map { e -> e.toDomain() }
            })
        } catch (e: Exception) {
            ResultOutput.Failure(Error.Database(e))
        }


    override suspend fun seedIfEmpty() : ResultOutput<Int, Error> =
        try {
            val count = parkingDao.count()
            if (count == 0) {
                val seed = listOf(
                    ParkingEntity(id = 1, name = "ParkEase Pro", pricePerHour = 5.0, rating = 4.9, distanceMins = 5, spots = 28),
                    ParkingEntity(id = 2, name = "AutoNest",     pricePerHour = 4.2, rating = 4.8, distanceMins = 10, spots = 10),
                    ParkingEntity(id = 3, name = "CityPark+",    pricePerHour = 6.0, rating = 4.7, distanceMins = 7, spots = 15)
                )
                parkingDao.insertAll(seed)
                ResultOutput.Success(count)
            } else {
                ResultOutput.Failure(Error.Validation("Data already seeded"))
            }
        } catch (e: Exception) {
            ResultOutput.Failure(Error.Database(e))
        }
}