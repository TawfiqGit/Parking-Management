package com.tawfiqdev.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.tawfiqdev.database.entity.EntryExitLogEntity
import com.tawfiqdev.database.entity.ParkingSpotEntity
import com.tawfiqdev.database.entity.PaymentEntity
import com.tawfiqdev.database.entity.ReservationEntity
import com.tawfiqdev.database.relations.ReservationFull
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservationDao {

    @Transaction
    @Query("SELECT * FROM reservations WHERE id = :reservationId")
    fun observeReservationFull(reservationId: Long): Flow<ReservationFull?>

    @Transaction
    @Query("SELECT * FROM reservations WHERE id = :reservationId")
    suspend fun getReservationFull(reservationId: Long): ReservationFull?

    @Transaction
    @Query("""
        SELECT * FROM reservations
        WHERE user_id = :userId
        ORDER BY start_time DESC
    """)
    fun observeReservationsForUser(userId: Long): Flow<List<ReservationFull>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReservation(entity: ReservationEntity): Long

    @Update
    suspend fun updateReservation(entity: ReservationEntity): Int

    @Query("DELETE FROM reservations WHERE id = :reservationId")
    suspend fun deleteById(reservationId: Long): Int

    // ---- WRITE (paiements / logs) ----
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayments(payments: List<PaymentEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayment(payment: PaymentEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntryExitLog(log: EntryExitLogEntity): Long
}