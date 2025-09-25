package com.tawfiqdev.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tawfiqdev.database.entity.ReviewEntity

@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(review: ReviewEntity): Long

    @Query("SELECT AVG(rating) FROM reviews WHERE parking_id = :parkingId")
    suspend fun getAverageRating(parkingId: Long): Double?
}