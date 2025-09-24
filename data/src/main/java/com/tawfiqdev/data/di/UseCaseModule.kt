package com.tawfiqdev.data.di

import com.tawfiqdev.domain.repository.LocationRepository
import com.tawfiqdev.domain.repository.ParkingRepository
import com.tawfiqdev.domain.repository.ReservationRepository
import com.tawfiqdev.domain.repository.VehicleRepository
import com.tawfiqdev.domain.usecase.ActivateReservationUseCase
import com.tawfiqdev.domain.usecase.AssignSpotUseCase
import com.tawfiqdev.domain.usecase.CancelReservationUseCase
import com.tawfiqdev.domain.usecase.CompleteReservationUseCase
import com.tawfiqdev.domain.usecase.CreateReservationAndGetSummaryUseCase
import com.tawfiqdev.domain.usecase.CreateReservationUseCase
import com.tawfiqdev.domain.usecase.FlowAllVehicleUseCase
import com.tawfiqdev.domain.usecase.GetReservationSummaryUseCase
import com.tawfiqdev.domain.usecase.GetVehicleByIdUseCase
import com.tawfiqdev.domain.usecase.InsertVehicleUseCase
import com.tawfiqdev.domain.usecase.IsSpotAvailableUseCase
import com.tawfiqdev.domain.usecase.MarkAsRecentUseCase
import com.tawfiqdev.domain.usecase.ObservePopularParkingUseCase
import com.tawfiqdev.domain.usecase.ObserveReservationsUseCase
import com.tawfiqdev.domain.usecase.RecentsUseCase
import com.tawfiqdev.domain.usecase.ReleaseSpotUseCase
import com.tawfiqdev.domain.usecase.ResultsUseCase
import com.tawfiqdev.domain.usecase.SeedParkingIfEmptyUseCase
import com.tawfiqdev.domain.usecase.SeedVehicleIfEmptyUseCase
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

    @Provides
    fun provideSeedVehicleIfEmptyUseCase(
        repo: VehicleRepository
    ) = SeedVehicleIfEmptyUseCase(repo)

    /**--ReservationRepository--**/
    @Provides
    fun provideCreateReservationUseCase(
        repo: ReservationRepository
    ) = CreateReservationUseCase(repo)

    @Provides
    fun provideGetReservationSummaryUseCase(
        repo: ReservationRepository
    ) = GetReservationSummaryUseCase(repo)

    @Provides
    fun provideCreateReservationAndGetSummaryUseCase(
        createReservation: CreateReservationUseCase,
        getSummary: GetReservationSummaryUseCase
    ) = CreateReservationAndGetSummaryUseCase(createReservation, getSummary)

    @Provides
    fun provideAssignSpotUseCase(
        repo: ReservationRepository
    ) = AssignSpotUseCase(repo)

    @Provides
    fun provideReleaseSpotUseCase(
        repo: ReservationRepository
    ) = ReleaseSpotUseCase(repo)

    @Provides
    fun provideCancelReservationUseCase(
        repo: ReservationRepository
    ) = CancelReservationUseCase(repo)

    @Provides
    fun provideCompleteReservationUseCase(
        repo: ReservationRepository
    ) = CompleteReservationUseCase(repo)

    @Provides
    fun provideActivateReservationUseCase(
        repo: ReservationRepository
    ) = ActivateReservationUseCase(repo)

    @Provides
    fun provideIsSpotAvailableUseCase(
        repo: ReservationRepository
    ) = IsSpotAvailableUseCase(repo)

    @Provides
    fun provideObserveReservationsUseCase(
        repo: ReservationRepository
    ) = ObserveReservationsUseCase(repo)
}