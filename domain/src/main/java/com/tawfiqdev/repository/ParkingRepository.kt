package com.tawfiqdev.repository

import com.tawfiqdev.model.Parking
import com.tawfiqdev.utils.ErrorState
import com.tawfiqdev.utils.ResultOutput
import kotlinx.coroutines.flow.Flow

interface ParkingRepository {
    fun observePopular(): Flow<List<Parking>>
    suspend fun seedIfEmpty() : ResultOutput<Int, ErrorState>
}
