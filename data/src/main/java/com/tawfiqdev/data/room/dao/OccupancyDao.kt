package com.tawfiqdev.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tawfiqdev.data.room.entity.OccupancyEntity
import com.tawfiqdev.data.room.entity.ReservationEntity
import com.tawfiqdev.domain.enums.ReservationStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface OccupancyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(occupancy: OccupancyEntity): Long

    @Query("""
        SELECT * FROM occupancy 
        WHERE spot_id = :spotId 
        ORDER BY at_millis DESC 
        LIMIT 1
    """)
    suspend fun getLastState(spotId: Long): OccupancyEntity?

    @Query("""
        SELECT o.* FROM occupancy o
        INNER JOIN (
            SELECT spot_id, MAX(at_millis) as max_at
            FROM occupancy
            GROUP BY spot_id
        ) latest
        ON o.spot_id = latest.spot_id AND o.at_millis = latest.max_at
        WHERE o.state = 'FREE' AND o.spot_id IN (:spotIds)
    """)
    suspend fun getFreeSpots(spotIds: List<Long>): List<OccupancyEntity>

    @Query("SELECT * FROM occupancy WHERE spot_id = :spotId ORDER BY at_millis DESC")
    fun observeHistory(spotId: Long): Flow<List<OccupancyEntity>>
}