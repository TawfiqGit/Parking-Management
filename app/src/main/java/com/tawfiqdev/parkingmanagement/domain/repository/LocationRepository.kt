package com.tawfiqdev.parkingmanagement.domain.repository

import com.tawfiqdev.parkingmanagement.domain.model.LocationSelection
import com.tawfiqdev.parkingmanagement.domain.utils.Error
import com.tawfiqdev.parkingmanagement.domain.utils.ResultOutput
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun observeRecent(): ResultOutput<Flow<List<LocationSelection>>, Error>
    suspend fun save(selection: LocationSelection): ResultOutput<Unit, Error>
}