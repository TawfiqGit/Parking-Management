package com.tawfiqdev.data.repository.occupancy

import com.tawfiqdev.data.room.entity.OccupancyEntity
import com.tawfiqdev.domain.enums.OccupancySource
import com.tawfiqdev.domain.enums.OccupancyState

interface OccupancyRepository {
    suspend fun markState(spotId: Long, state: OccupancyState, source: OccupancySource = OccupancySource.APP)
    suspend fun getLastState(spotId: Long): OccupancyEntity?
}