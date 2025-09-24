package com.tawfiqdev.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.tawfiqdev.data.room.entity.ReservationEntity
import com.tawfiqdev.data.room.entity.ReservationWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ReservationEntity): Long

    @Update
    suspend fun update(entity: ReservationEntity)

    @Query("SELECT * FROM reservation WHERE id = :id")
    suspend fun getById(id: Long): ReservationEntity?

    @Query("""
        UPDATE reservation SET spot_id = :spotId WHERE id = :reservationId
    """)
    suspend fun setSpot(reservationId: Long, spotId: Long?)

    @Query("""
        UPDATE reservation SET status = :status WHERE id = :reservationId
    """)
    suspend fun setStatus(reservationId: Long, status: String)

    @Query("SELECT * FROM reservation ORDER BY starts_at DESC")
    fun observeByUser(): Flow<List<ReservationEntity>>

    // Détection de chevauchement sur une place
    @Query("""
        SELECT COUNT(*) FROM reservation 
        WHERE spot_id = :spotId
          AND status IN ('BOOKED','ACTIVE')
          AND NOT (ends_at <= :startsAt OR starts_at >= :endsAt)
    """)
    suspend fun countConflicts(spotId: Long, startsAt: Long, endsAt: Long): Int

    @Transaction
    @Query("SELECT * FROM reservation WHERE id = :reservationId")
    suspend fun getReservationWithDetails(reservationId: Long): ReservationWithDetails
}