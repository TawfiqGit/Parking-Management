package com.tawfiqdev.data.di

import com.tawfiqdev.data.repository.LocationRepositoryImpl
import com.tawfiqdev.data.repository.ParkingRepositoryImpl
import com.tawfiqdev.data.repository.VehicleRepositoryImpl
import com.tawfiqdev.domain.repository.ReservationRepository
import com.tawfiqdev.data.repository.ReservationRepositoryImpl
import com.tawfiqdev.domain.repository.LocationRepository
import com.tawfiqdev.domain.repository.ParkingRepository
import com.tawfiqdev.domain.repository.VehicleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindVehicleRepository(impl: VehicleRepositoryImpl): VehicleRepository

    @Binds
    @Singleton
    abstract fun bindParkingRepository(impl: ParkingRepositoryImpl): ParkingRepository

    @Binds
    @Singleton
    abstract fun bindLocationRepository(impl: LocationRepositoryImpl): LocationRepository

    @Binds
    @Singleton
    abstract fun bindReservationRepository(impl: ReservationRepositoryImpl): ReservationRepository
}