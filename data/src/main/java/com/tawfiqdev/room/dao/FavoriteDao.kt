package com.tawfiqdev.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tawfiqdev.room.entity.FavoriteParkingEntity
import com.tawfiqdev.room.entity.ParkingEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(fav: FavoriteParkingEntity): Long

    @Query("DELETE FROM favorite_parkings WHERE user_id = :userId AND parking_id = :parkingId")
    suspend fun removeFavorite(userId: Long, parkingId: Long)

    @Query("""
        SELECT p.* FROM parkings p
        INNER JOIN favorite_parkings f ON f.parking_id = p.id
        WHERE f.user_id = :userId
        ORDER BY f.created_at DESC
    """)
    suspend fun getFavorites(userId: Long): List<ParkingEntity>
}