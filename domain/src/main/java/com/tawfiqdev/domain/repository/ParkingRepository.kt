package com.tawfiqdev.domain.repository

import com.tawfiqdev.domain.model.Parking
import com.tawfiqdev.domain.utils.ResultOutput
import kotlinx.coroutines.flow.Flow
import com.tawfiqdev.domain.utils.Error

interface ParkingRepository {
    suspend fun observePopular(): Flow<List<Parking>>
    suspend fun seedIfEmpty() : ResultOutput<Int, Error>
}
