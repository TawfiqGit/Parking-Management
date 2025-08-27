package com.tawfiqdev.parkingmanagement.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tawfiqdev.parkingmanagement.data.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insert(userEntity: UserEntity)

    @Update
    suspend fun update(userEntity: UserEntity)

    @Query("DELETE FROM user")
    suspend fun delete(userEntity: UserEntity)

    @Query("SELECT * FROM user WHERE id ")
    fun observeUser(): Flow<List<UserEntity>>
}