package com.tawfiqdev.di

import com.tawfiqdev.repository.LocationRepository
import com.tawfiqdev.repository.ParkingRepository
import com.tawfiqdev.repository.UserRepository
import com.tawfiqdev.repository.VehicleRepository
import com.tawfiqdev.usecase.DeleteUserUseCase
import com.tawfiqdev.usecase.FlowAllVehicleUseCase
import com.tawfiqdev.usecase.GetUserByIdUseCase
import com.tawfiqdev.usecase.GetVehicleByIdUseCase
import com.tawfiqdev.usecase.InsertUserUseCase
import com.tawfiqdev.usecase.InsertVehicleUseCase
import com.tawfiqdev.usecase.MarkAsRecentUseCase
import com.tawfiqdev.usecase.ObservePopularParkingUseCase
import com.tawfiqdev.usecase.RecentsUseCase
import com.tawfiqdev.usecase.ResultsUseCase
import com.tawfiqdev.usecase.SeedParkingIfEmptyUseCase
import com.tawfiqdev.usecase.SuggestionsUseCase
import com.tawfiqdev.usecase.UpdateUserUseCase
import com.tawfiqdev.usecase.UpdateVehicleUseCase
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

    /**--UserRepository--**/

    @Provides
    fun provideGetUserByIdUseCase(
        repo: UserRepository
    ) = GetUserByIdUseCase(repo)

    @Provides
    fun provideInsertUserUseCase(
        repo: UserRepository
    ) = InsertUserUseCase(repo)

    @Provides
    fun provideUpdateUserUseCase(
        repo: UserRepository
    ) = UpdateUserUseCase(repo)

    @Provides
    fun provideDeleteUserUseCase(
        repo: UserRepository
    ) = DeleteUserUseCase(repo)
}