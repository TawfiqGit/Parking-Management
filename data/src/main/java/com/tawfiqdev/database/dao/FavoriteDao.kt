package com.tawfiqdev.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tawfiqdev.database.entity.FavoriteParkingEntity
import com.tawfiqdev.database.entity.ParkingEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(fav: FavoriteParkingEntity): Long

    @Query("DELETE FROM favorite_parking WHERE user_id = :userId AND parking_id = :parkingId")
    suspend fun removeFavorite(userId: Long, parkingId: Long)

    @Query("""
        SELECT p.* FROM parking p
        INNER JOIN favorite_parking f ON f.parking_id = p.id
        WHERE f.user_id = :userId
        ORDER BY f.created_at DESC
    """)
    suspend fun getFavorites(userId: Long): List<ParkingEntity>
}