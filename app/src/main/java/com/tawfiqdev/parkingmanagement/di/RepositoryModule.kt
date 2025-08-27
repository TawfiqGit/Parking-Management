package com.tawfiqdev.parkingmanagement.di

import com.tawfiqdev.parkingmanagement.data.repository.ReservationRepository
import com.tawfiqdev.parkingmanagement.data.repository.ReservationRepositoryImpl
import com.tawfiqdev.parkingmanagement.data.room.ParkingMgmtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideReservationRepository(db: ParkingMgmtDatabase): ReservationRepository = ReservationRepositoryImpl(db)
}