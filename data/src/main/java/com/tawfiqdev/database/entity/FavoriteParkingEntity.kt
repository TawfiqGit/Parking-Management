package com.tawfiqdev.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "favorite_parking",
    primaryKeys = ["user_id","parking_id"],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ParkingEntity::class,
            parentColumns = ["id"],
            childColumns = ["parking_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index("user_id"), Index("parking_id")]
)
data class FavoriteParkingEntity(
    @ColumnInfo(name = "user_id") val userId: Long,
    @ColumnInfo(name = "parking_id") val parkingId: Long,
    @ColumnInfo(name = "created_at") val createdAt: Long
)