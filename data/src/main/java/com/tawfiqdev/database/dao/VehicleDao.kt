package com.tawfiqdev.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tawfiqdev.database.entity.VehicleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {
    @Insert
    suspend fun insert(vehicleEntity: VehicleEntity) : Long

    @Update
    suspend fun update(vehicleEntity: VehicleEntity)

    @Delete
    suspend fun delete(vehicleEntity: VehicleEntity)

    @Query("SELECT * FROM vehicles")
    fun observeAllCar(): Flow<List<VehicleEntity>>

    @Query("SELECT * FROM vehicles WHERE id = :id")
    fun observeCarById(id: Long): Flow<VehicleEntity>

    @Query("SELECT * FROM vehicles ORDER BY brand, model")
    fun observeCarByModel(): Flow<List<VehicleEntity>>
}