package com.tawfiqdev.parkingmanagement.di

import android.content.Context
import androidx.room.Room
import com.tawfiqdev.parkingmanagement.data.room.db.ParkingMgmtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/***
 * Sert à expliquer à Hilt comment construire Database + DAOs.*
 * **/
@Module // Utilisée pour regrouper et fournir des dépendances
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides //Créez l'objet vous-même
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : ParkingMgmtDatabase {
        return Room
            .databaseBuilder(
                appContext,
                ParkingMgmtDatabase::class.java,
                "parking_mgmt_db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideCarDao(db: ParkingMgmtDatabase) = db.vehicleDao()

    @Provides
    fun provideUserDao(db: ParkingMgmtDatabase) = db.userDao()

    @Provides
    fun provideReservationDao(db: ParkingMgmtDatabase) = db.reservationDao()

    @Provides
    fun provideParkingDao(db: ParkingMgmtDatabase) = db.parkingDao()

    @Provides
    fun provideLocationDao(db: ParkingMgmtDatabase) = db.locationDao()
}