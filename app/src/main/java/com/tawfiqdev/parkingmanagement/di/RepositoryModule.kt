package com.tawfiqdev.parkingmanagement.di

import com.tawfiqdev.parkingmanagement.data.repository.LocationRepositoryImpl
import com.tawfiqdev.parkingmanagement.data.repository.ParkingRepositoryImpl
import com.tawfiqdev.parkingmanagement.data.repository.VehicleRepositoryImpl
import com.tawfiqdev.parkingmanagement.domain.repository.LocationRepository
import com.tawfiqdev.parkingmanagement.domain.repository.ParkingRepository
import com.tawfiqdev.parkingmanagement.domain.repository.VehicleRepository
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