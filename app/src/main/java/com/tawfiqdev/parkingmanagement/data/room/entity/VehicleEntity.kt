package com.tawfiqdev.parkingmanagement.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "vehicle" ,
    indices = [Index(value = ["registration_plate"], unique = true)]
)
data class VehicleEntity (
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "registration_plate") val registrationPlate : String,
    @ColumnInfo(name = "brand") val marque : String,
    @ColumnInfo(name = "model") val model : String,
    @ColumnInfo(name = "seats") val siege: Int,
    @ColumnInfo(name = "status") val statusCar: StatusCar = StatusCar.AVAILABLE,
)

enum class StatusCar {
    AVAILABLE,
    UNAVAILABLE,
    MAINTENANCE,
}