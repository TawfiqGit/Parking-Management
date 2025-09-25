package com.tawfiqdev.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tawfiqdev.database.entity.PaymentEntity

@Dao
interface PaymentDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(payment: PaymentEntity): Long

    @Query("""
        SELECT * FROM payments
        WHERE reservation_id = :reservationId 
        ORDER BY created_at DESC
    """)
    suspend fun forReservation(reservationId: Long): List<PaymentEntity>
}