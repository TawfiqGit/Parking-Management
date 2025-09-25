package com.tawfiqdev.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "reviews",
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
    indices = [Index("parking_id"), Index("user_id")]
)
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "user_id") val userId: Long,
    @ColumnInfo(name = "parking_id") val parkingId: Long,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    val rating: Int,           // 1..5 (enforce côté code)
    val comment: String?,
)