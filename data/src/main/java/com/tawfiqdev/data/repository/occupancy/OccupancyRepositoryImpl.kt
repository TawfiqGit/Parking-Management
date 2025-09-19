package com.tawfiqdev.data.repository.occupancy

import com.tawfiqdev.data.room.dao.OccupancyDao
import com.tawfiqdev.data.room.entity.OccupancyEntity
import com.tawfiqdev.domain.enums.OccupancySource
import com.tawfiqdev.domain.enums.OccupancyState
import javax.inject.Inject

class OccupancyRepositoryImpl @Inject constructor(
    private val occupancyDao: OccupancyDao
) : OccupancyRepository {

    override suspend fun markState(spotId: Long, state: OccupancyState, source: OccupancySource) {
        occupancyDao.insert(
            OccupancyEntity(
                spotId = spotId,
                state = state.name,
                source = source.name,
                atMillis = System.currentTimeMillis()
            )
        )
    }

    override suspend fun getLastState(spotId: Long): OccupancyEntity? =
        occupancyDao.getLastState(spotId)

}