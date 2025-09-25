package com.tawfiqdev.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.tawfiqdev.database.entity.ParkingEntity
import com.tawfiqdev.database.entity.ParkingRateEntity
import com.tawfiqdev.database.entity.ParkingSpotEntity
import com.tawfiqdev.database.relations.ParkingWithSpots
import kotlinx.coroutines.flow.Flow

@Dao
interface ParkingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ParkingEntity>)

    @Query("SELECT COUNT(*) FROM parking")
    suspend fun count(): Int

    @Query("SELECT * FROM parking")
    fun observeParking(): Flow<List<ParkingEntity>>

    @Query("SELECT * FROM parking ORDER BY spots")
    fun observeParkingBySpot(): Flow<List<ParkingEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertParking(entity: ParkingEntity): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertSpot(entity: ParkingSpotEntity): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertRate(entity: ParkingRateEntity): Long

    @Transaction
    @Query("SELECT * FROM parking WHERE id = :id")
    suspend fun getParkingWithSpots(id: Long): ParkingWithSpots?

    @Query("SELECT * FROM parking_spots WHERE parking_id = :parkingId AND is_active = 1")
    suspend fun getActiveSpots(parkingId: Long): List<ParkingSpotEntity>
}