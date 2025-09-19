package com.tawfiqdev.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tawfiqdev.data.room.entity.VehicleEntity
import com.tawfiqdev.domain.enums.StatusCar
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {
    @Insert
    suspend fun insert(vehicleEntity: VehicleEntity) : Long

    @Update
    suspend fun update(vehicleEntity: VehicleEntity)

    @Delete
    suspend fun delete(vehicleEntity: VehicleEntity)

    @Query("UPDATE vehicle SET status = :statusCar WHERE id = :id")
    suspend fun updateStatus(id: Long, statusCar: StatusCar)

    @Query("SELECT * FROM vehicle")
    fun observeAllCar(): Flow<List<VehicleEntity>>

    @Query("SELECT * FROM vehicle WHERE id = :id")
    fun observeCarById(id: Long): Flow<VehicleEntity>

    @Query("SELECT * FROM vehicle ORDER BY brand, model")
    fun observeCarByModel(): Flow<List<VehicleEntity>>
}