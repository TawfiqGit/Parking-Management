package com.tawfiqdev.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tawfiqdev.data.room.entity.ReservationEntity
import com.tawfiqdev.domain.enums.ReservationStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reservationEntity: ReservationEntity) : Long

    @Update
    suspend fun update(reservationEntity: ReservationEntity)

    @Delete
    suspend fun delete(reservationEntity: ReservationEntity)

    @Query("SELECT * FROM reservation WHERE id = :id")
    suspend fun getById(id: Long): ReservationEntity?

    @Query("SELECT * FROM reservation WHERE user_id = :userId ORDER BY starts_at DESC")
    fun observeByUser(userId: Long): Flow<List<ReservationEntity>>
    
    // Vérifier si une place est déjà réservée sur une période
    @Query("""
        SELECT COUNT(*) FROM reservation 
        WHERE spot_id = :spotId 
        AND status IN ('BOOKED','ACTIVE') 
        AND NOT (ends_at <= :startsAt OR starts_at >= :endsAt)
    """)
    suspend fun countConflicts(spotId: Long, startsAt: Long, endsAt: Long): Int
}