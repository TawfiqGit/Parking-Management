package com.tawfiqdev.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tawfiqdev.database.Converters
import com.tawfiqdev.database.dao.EntryExitDao
import com.tawfiqdev.database.dao.FavoriteDao
import com.tawfiqdev.database.dao.LocationDao
import com.tawfiqdev.database.dao.ParkingDao
import com.tawfiqdev.database.dao.PaymentDao
import com.tawfiqdev.database.dao.ReservationDao
import com.tawfiqdev.database.dao.ReviewDao
import com.tawfiqdev.database.dao.UserDao
import com.tawfiqdev.database.dao.VehicleDao
import com.tawfiqdev.database.entity.EntryExitLogEntity
import com.tawfiqdev.database.entity.FavoriteParkingEntity
import com.tawfiqdev.database.entity.LocationEntity
import com.tawfiqdev.database.entity.LocationFts
import com.tawfiqdev.database.entity.ParkingEntity
import com.tawfiqdev.database.entity.ParkingRateEntity
import com.tawfiqdev.database.entity.ParkingSpotEntity
import com.tawfiqdev.database.entity.PaymentEntity
import com.tawfiqdev.database.entity.RecentLocationEntity
import com.tawfiqdev.database.entity.ReservationEntity
import com.tawfiqdev.database.entity.ReviewEntity
import com.tawfiqdev.database.entity.UserEntity
import com.tawfiqdev.database.entity.VehicleEntity

@Database(
    entities = [
        UserEntity::class,
        ParkingEntity::class,
        LocationEntity::class,
        LocationFts::class,
        RecentLocationEntity::class,
        VehicleEntity::class,
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
    abstract fun reviewDao(): ReviewDao
    abstract fun paymentDao(): PaymentDao
    abstract fun entryExitDao(): EntryExitDao
    abstract fun favoriteParkingDao(): FavoriteDao
}