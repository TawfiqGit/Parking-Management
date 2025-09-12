package com.tawfiqdev.parkingmanagement.di

import com.tawfiqdev.parkingmanagement.domain.repository.LocationRepository
import com.tawfiqdev.parkingmanagement.domain.repository.ParkingRepository
import com.tawfiqdev.parkingmanagement.domain.repository.VehicleRepository
import com.tawfiqdev.parkingmanagement.domain.usecase.MarkAsRecentUseCase
import com.tawfiqdev.parkingmanagement.domain.usecase.ObservePopularParkingUseCase
import com.tawfiqdev.parkingmanagement.domain.usecase.RecentsUseCase
import com.tawfiqdev.parkingmanagement.domain.usecase.ResultsUseCase
import com.tawfiqdev.parkingmanagement.domain.usecase.SeedIfEmptyUseCase
import com.tawfiqdev.parkingmanagement.domain.usecase.SeedParkingIfEmptyUseCase
import com.tawfiqdev.parkingmanagement.domain.usecase.SuggestionsUseCase
import com.tawfiqdev.parkingmanagement.domain.usecase.vehicle.FlowAllVehicleUseCase
import com.tawfiqdev.parkingmanagement.domain.usecase.vehicle.GetVehicleByIdUseCase
import com.tawfiqdev.parkingmanagement.domain.usecase.vehicle.InsertVehicleUseCase
import com.tawfiqdev.parkingmanagement.domain.usecase.vehicle.UpdateVehicleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideFlowAllVehicleUseCase(repo: VehicleRepository) = FlowAllVehicleUseCase(repo)

    @Provides
    @Singleton
    fun provideGetVehicleByIdUseCase(repo: VehicleRepository) = GetVehicleByIdUseCase(repo)

    @Provides
    @Singleton
    fun provideInsertVehicleUseCase(repo: VehicleRepository) = InsertVehicleUseCase(repo)

    @Provides
    @Singleton
    fun provideUpdateVehicleUseCase(repo: VehicleRepository) = UpdateVehicleUseCase(repo)

    @Provides
    @Singleton
    fun provideObservePopular(repo: ParkingRepository) = ObservePopularParkingUseCase(repo)

    @Provides
    @Singleton
    fun provideSeedParkingIfEmpty(repo: ParkingRepository) = SeedParkingIfEmptyUseCase(repo)

    @Provides
    @Singleton
    fun provideSuggestions(repo: LocationRepository) = SuggestionsUseCase(repo)

    @Provides
    @Singleton
    fun provideResults(repo: LocationRepository) = ResultsUseCase(repo)

    @Provides
    @Singleton
    fun provideRecents(repo: LocationRepository) = RecentsUseCase(repo)

    @Provides
    @Singleton
    fun provideMarkAsRecent(repo: LocationRepository) = MarkAsRecentUseCase(repo)

    @Provides
    @Singleton
    fun provideSeedIfEmpty(repo: LocationRepository) = SeedIfEmptyUseCase(repo)
}