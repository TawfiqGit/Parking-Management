package com.tawfiqdev.parkingmanagement.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tawfiqdev.parkingmanagement.data.room.entity.ParkingEntity
import com.tawfiqdev.parkingmanagement.data.room.entity.VehicleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ParkingDao {
    @Insert
    suspend fun insert(parkingEntity: ParkingEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ParkingEntity>)

    @Query("SELECT COUNT(*) FROM parkings")
    suspend fun count(): Int

    @Query("SELECT * FROM parkings")
    fun observeParking(): Flow<List<ParkingEntity>>

    @Query("SELECT * FROM parkings ORDER BY spots")
    fun observeParkingBySpot(): Flow<List<ParkingEntity>>

    @Query("SELECT * FROM parkings WHERE id = :id LIMIT 1")
    suspend fun getParkingById(id: Int): Flow<ParkingEntity>
}