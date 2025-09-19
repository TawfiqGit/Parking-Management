package com.tawfiqdev.data.di

import com.tawfiqdev.domain.repository.LocationRepository
import com.tawfiqdev.domain.repository.ParkingRepository
import com.tawfiqdev.domain.repository.VehicleRepository
import com.tawfiqdev.domain.usecase.FlowAllVehicleUseCase
import com.tawfiqdev.domain.usecase.GetVehicleByIdUseCase
import com.tawfiqdev.domain.usecase.InsertVehicleUseCase
import com.tawfiqdev.domain.usecase.MarkAsRecentUseCase
import com.tawfiqdev.domain.usecase.ObservePopularParkingUseCase
import com.tawfiqdev.domain.usecase.RecentsUseCase
import com.tawfiqdev.domain.usecase.ResultsUseCase
import com.tawfiqdev.domain.usecase.SeedParkingIfEmptyUseCase
import com.tawfiqdev.domain.usecase.SuggestionsUseCase
import com.tawfiqdev.domain.usecase.UpdateVehicleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    /**--ParkingRepository--**/
    @Provides
    fun provideObservePopularParkingUseCase(
        repo: ParkingRepository
    ) = ObservePopularParkingUseCase(repo)

    @Provides
    fun provideSeedParkingIfEmptyUseCase(
        repo: ParkingRepository
    ) = SeedParkingIfEmptyUseCase(repo)

    /**--LocationRepository--**/
    @Provides
    fun provideResultsUseCase(
        repo: LocationRepository
    ) = ResultsUseCase(repo)

    @Provides
    fun provideSuggestionsUseCase(
        repo: LocationRepository
    ) = SuggestionsUseCase(repo)

    @Provides
    fun provideRecentsUseCase(
        repo: LocationRepository
    ) = RecentsUseCase(repo)

    @Provides
    fun provideMarkAsRecentUseCase(
        repo: LocationRepository
    ) = MarkAsRecentUseCase(repo)


    /**--VehicleRepository--**/
    @Provides
    fun provideFlowAllVehicleUseCase(
        repo: VehicleRepository
    ) = FlowAllVehicleUseCase(repo)

    @Provides
    fun provideGetVehicleByIdUseCase(
        repo: VehicleRepository
    ) = GetVehicleByIdUseCase(repo)

    @Provides
    fun provideInsertVehicleUseCase(
        repo: VehicleRepository
    ) = InsertVehicleUseCase(repo)

    @Provides
    fun provideUpdateVehicleUseCase(
        repo: VehicleRepository
    ) = UpdateVehicleUseCase(repo)
}