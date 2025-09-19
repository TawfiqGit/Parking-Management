package com.tawfiqdev.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tawfiqdev.data.converts.Converters
import com.tawfiqdev.data.room.dao.LocationDao
import com.tawfiqdev.data.room.dao.ParkingDao
import com.tawfiqdev.data.room.dao.ReservationDao
import com.tawfiqdev.data.room.dao.UserDao
import com.tawfiqdev.data.room.dao.VehicleDao
import com.tawfiqdev.data.room.entity.LocationEntity
import com.tawfiqdev.data.room.entity.LocationFts
import com.tawfiqdev.data.room.entity.ParkingEntity
import com.tawfiqdev.data.room.entity.RecentLocationEntity
import com.tawfiqdev.data.room.entity.ReservationEntity
import com.tawfiqdev.data.room.entity.UserEntity
import com.tawfiqdev.data.room.entity.VehicleEntity

@Database(
    entities = [VehicleEntity::class , ReservationEntity::class , UserEntity::class, ParkingEntity::class, LocationEntity::class, LocationFts::class, RecentLocationEntity::class ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ParkingMgmtDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
    abstract fun reservationDao(): ReservationDao
    abstract fun userDao(): UserDao
    abstract fun parkingDao(): ParkingDao
    abstract fun locationDao(): LocationDao
}