package com.tawfiqdev.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.tawfiqdev.enums.Category

@Entity(
    tableName = "parking" ,
    indices = [Index(value = ["name"], unique = true)]
)
data class ParkingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "category")  val category: Category = Category.CAR,
    @ColumnInfo(name = "pricePerHour")  val pricePerHour: Double,
    @ColumnInfo(name = "rating") val rating: Double,
    @ColumnInfo(name = "distanceMins")  val distanceMins: Int,
    @ColumnInfo(name = "spots")  val spots: Int,
    @ColumnInfo(name = "imageRes") val imageRes: Int? = null,
    @ColumnInfo(name = "imageUrl")  val imageUrl: String? = null,
    @ColumnInfo(name = "address")  val address: String,
    @ColumnInfo(name = "reviewCount")  val reviewCount: Int? = null
)