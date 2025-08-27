package com.tawfiqdev.parkingmanagement.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tawfiqdev.parkingmanagement.data.room.entity.ReservationEntity
import com.tawfiqdev.parkingmanagement.data.room.entity.StatusRez
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservationDao {

    @Insert
    suspend fun insert(reservationEntity: ReservationEntity) : Long

    @Update
    suspend fun update(reservationEntity: ReservationEntity)

    @Delete
    suspend fun delete(reservationEntity: ReservationEntity)

    @Query("UPDATE reservation SET status = :statusRez  WHERE id = :id")
    suspend fun updateStatus(id: Long, statusRez: StatusRez)

    @Query("SELECT * FROM reservation")
    fun observeReservation(): Flow<List<ReservationEntity>>
}