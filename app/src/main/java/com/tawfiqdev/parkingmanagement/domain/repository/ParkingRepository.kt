package com.tawfiqdev.parkingmanagement.domain.repository

import com.tawfiqdev.parkingmanagement.domain.model.Parking
import com.tawfiqdev.parkingmanagement.domain.model.Vehicle
import com.tawfiqdev.parkingmanagement.domain.utils.Error
import com.tawfiqdev.parkingmanagement.domain.utils.ResultOutput
import kotlinx.coroutines.flow.Flow

interface ParkingRepository {
    fun observePopular(): ResultOutput<Flow<List<Parking>>, Error>
    suspend fun seedIfEmpty() : ResultOutput<Int, Error>
}
