package com.tawfiqdev.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.tawfiqdev.data.room.entity.ParkingEntity
import com.tawfiqdev.data.room.entity.ParkingSpotEntity
import com.tawfiqdev.data.room.entity.SpotTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ParkingDao {

    @Insert
    suspend fun insert(parkingEntity: ParkingEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ParkingEntity>)

    @Query("SELECT COUNT(*) FROM parkings")
    suspend fun count(): Int

    @Query("SELECT * FROM spot_type ORDER BY code")
    suspend fun getAllSpotTypes(): List<SpotTypeEntity>

    @Query("SELECT * FROM parkings")
    fun observeParking(): Flow<List<ParkingEntity>>

    @Query("SELECT * FROM parking_spots WHERE parking_id = :parkingId ORDER BY spot_code")
    fun observeSpots(parkingId: Long): Flow<List<ParkingSpotEntity>>

    @Transaction
    @Query("SELECT * FROM parkings ORDER BY created_at DESC")
    fun observeParkingByCreatedAt(): Flow<List<ParkingEntity>>
}