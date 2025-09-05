package com.tawfiqdev.parkingmanagement.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tawfiqdev.parkingmanagement.data.room.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Insert
    suspend fun insert(locationEntity: LocationEntity) : Long

    @Query("SELECT * FROM locations")
    fun observeLocation(): Flow<List<LocationEntity>>
}