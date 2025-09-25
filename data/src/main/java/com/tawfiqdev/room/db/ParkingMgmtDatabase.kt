package com.tawfiqdev.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tawfiqdev.converts.Converters
import com.tawfiqdev.room.dao.LocationDao
import com.tawfiqdev.room.dao.ParkingDao
import com.tawfiqdev.room.dao.ReservationDao
import com.tawfiqdev.room.dao.UserDao
import com.tawfiqdev.room.dao.VehicleDao
import com.tawfiqdev.room.entity.EntryExitLogEntity
import com.tawfiqdev.room.entity.FavoriteParkingEntity
import com.tawfiqdev.room.entity.LocationEntity
import com.tawfiqdev.room.entity.LocationFts
import com.tawfiqdev.room.entity.ParkingEntity
import com.tawfiqdev.room.entity.ParkingRateEntity
import com.tawfiqdev.room.entity.ParkingSpotEntity
import com.tawfiqdev.room.entity.PaymentEntity
import com.tawfiqdev.room.entity.RecentLocationEntity
import com.tawfiqdev.room.entity.ReservationEntity
import com.tawfiqdev.room.entity.ReviewEntity
import com.tawfiqdev.room.entity.UserEntity
import com.tawfiqdev.room.entity.VehicleEntity

@Database(
    entities = [
        UserEntity::class,
        ParkingEntity::class,
        LocationEntity::class,
        LocationFts::class,
        RecentLocationEntity::class,
        VehicleEntity::class,
        ParkingEntity::class,
        ParkingSpotEntity::class,
        ParkingRateEntity::class,
        ReservationEntity::class,
        PaymentEntity::class,
        EntryExitLogEntity::class,
        FavoriteParkingEntity::class,
        ReviewEntity::class
    ],
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