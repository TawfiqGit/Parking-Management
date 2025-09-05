package com.tawfiqdev.parkingmanagement.data.repository

import com.tawfiqdev.parkingmanagement.data.mapper.toDomain
import com.tawfiqdev.parkingmanagement.data.mapper.toEntity
import com.tawfiqdev.parkingmanagement.data.room.dao.LocationDao
import com.tawfiqdev.parkingmanagement.domain.model.LocationSelection
import com.tawfiqdev.parkingmanagement.domain.model.Parking
import com.tawfiqdev.parkingmanagement.domain.repository.LocationRepository
import com.tawfiqdev.parkingmanagement.domain.utils.Error
import com.tawfiqdev.parkingmanagement.domain.utils.ResultOutput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.map

class LocationRepositoryImpl @Inject constructor(
    private val locationDao: LocationDao
): LocationRepository {

    override fun observeRecent(): ResultOutput<Flow<List<LocationSelection>>, Error>  =
        try {
            val entity =  locationDao.observeLocation()
            ResultOutput.Success(value =
                entity.map {
                    it.map { e -> e.toDomain() }
                }
            )
        } catch (e: Exception) {
            ResultOutput.Failure(Error.Database(e))
        }

    override suspend fun save(selection: LocationSelection): ResultOutput<Unit, Error>  =
        try {
            locationDao.insert(selection.toEntity())
            ResultOutput.Success(Unit)
        } catch (e: Exception) {
            ResultOutput.Failure(Error.Database(e))
        }
}