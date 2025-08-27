package com.tawfiqdev.parkingmanagement.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tawfiqdev.parkingmanagement.data.room.dao.VehicleDao
import com.tawfiqdev.parkingmanagement.data.room.dao.ReservationDao
import com.tawfiqdev.parkingmanagement.data.room.dao.UserDao
import com.tawfiqdev.parkingmanagement.data.room.entity.VehicleEntity
import com.tawfiqdev.parkingmanagement.data.room.entity.ReservationEntity
import com.tawfiqdev.parkingmanagement.data.room.entity.UserEntity

@Database(
    entities = [VehicleEntity::class , ReservationEntity::class , UserEntity::class ],
    version = 1,
    exportSchema = false
)
abstract class ParkingMgmtDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
    abstract fun reservationDao(): ReservationDao
    abstract fun userDao(): UserDao
}