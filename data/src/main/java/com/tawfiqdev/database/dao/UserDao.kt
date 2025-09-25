package com.tawfiqdev.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.tawfiqdev.database.entity.UserEntity
import com.tawfiqdev.database.relations.UserWithVehicles

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: UserEntity): Long

    @Update suspend fun update(user: UserEntity)
    @Delete suspend fun delete(user: UserEntity)

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getById(id: Long): UserEntity?

    @Transaction
    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getWithVehicles(id: Long): UserWithVehicles?
}