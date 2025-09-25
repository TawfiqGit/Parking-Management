package com.tawfiqdev.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tawfiqdev.database.entity.EntryExitLogEntity

@Dao
interface EntryExitDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(log: EntryExitLogEntity): Long

    @Query("UPDATE entry_exit_logs SET entry_time = :time WHERE reservation_id = :reservationId")
    suspend fun markEntry(reservationId: Long, time: Long)

    @Query("UPDATE entry_exit_logs SET exit_time = :time WHERE reservation_id = :reservationId")
    suspend fun markExit(reservationId: Long, time: Long)
}