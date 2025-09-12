package com.tawfiqdev.parkingmanagement.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tawfiqdev.parkingmanagement.data.room.entity.LocationEntity
import com.tawfiqdev.parkingmanagement.data.room.entity.ParkingEntity
import com.tawfiqdev.parkingmanagement.data.room.entity.RecentLocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upsertAll(items: List<LocationEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upsert(item: LocationEntity): Long

    @Query("SELECT * FROM locations WHERE id = :id")
    suspend fun getById(id: Int): LocationEntity?

    @Query("""
        SELECT l.* FROM locations l
        LIMIT :limit
    """)
    suspend fun searchFts(limit: Int = 20): List<LocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRecent(item: RecentLocationEntity)

    @Query("""
        SELECT l.* FROM recent_locations r
        JOIN locations l ON l.id = r.locationId
        ORDER BY r.lastUsedAt DESC
        LIMIT :limit
    """)
    suspend fun getRecents(limit: Int = 10): List<LocationEntity>

    @Query("DELETE FROM recent_locations")
    suspend fun clearRecents()
}