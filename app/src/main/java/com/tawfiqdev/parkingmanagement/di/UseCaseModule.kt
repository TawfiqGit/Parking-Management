package com.tawfiqdev.parkingmanagement.di

import com.tawfiqdev.parkingmanagement.domain.repository.VehicleRepository
import com.tawfiqdev.parkingmanagement.domain.usecase.FlowAllVehicleUseCase
import com.tawfiqdev.parkingmanagement.domain.usecase.GetVehicleByIdUseCase
import com.tawfiqdev.parkingmanagement.domain.usecase.InsertVehicleUseCase
import com.tawfiqdev.parkingmanagement.domain.usecase.UpdateVehicleUseCase
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
}