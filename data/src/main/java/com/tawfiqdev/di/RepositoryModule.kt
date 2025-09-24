package com.tawfiqdev.di

import com.tawfiqdev.repository.LocationRepository
import com.tawfiqdev.repository.LocationRepositoryImpl
import com.tawfiqdev.repository.ParkingRepository
import com.tawfiqdev.repository.ParkingRepositoryImpl
import com.tawfiqdev.repository.VehicleRepository
import com.tawfiqdev.repository.VehicleRepositoryImpl
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
}