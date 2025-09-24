package com.tawfiqdev.parkingmanagement.presentation.booking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawfiqdev.domain.model.Reservation
import com.tawfiqdev.domain.model.ReservationSummary
import com.tawfiqdev.domain.usecase.CreateReservationAndGetSummaryUseCase
import com.tawfiqdev.domain.usecase.ObserveReservationsUseCase
import com.tawfiqdev.parkingmanagement.presentation.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val createAndGet: CreateReservationAndGetSummaryUseCase,
    private val observeReservationsUS : ObserveReservationsUseCase
) : ViewModel() {
    private val _actionState = MutableStateFlow<UiState>(UiState.Idle)
    val actionState: StateFlow<UiState> = _actionState

    private val _reservationState = MutableStateFlow<UiState>(UiState.Loading)
    val reservationState: StateFlow<UiState> = _reservationState.asStateFlow()

    init {
        observeReservation()
    }

    fun create(
        vehicle: Long,
        parking: Long,
        spot: Long?,
        start: Long,
        end: Long,
        expectedPriceCents: Long?
    ) {
        viewModelScope.launch {
            _actionState.value = UiState.Loading
            try {
                val summary = createAndGet(
                    CreateReservationAndGetSummaryUseCase.Params(
                        vehicleId = vehicle,
                        parkingId = parking,
                        spotId = spot,
                        start = start,
                        end = end,
                        expectedPriceCents = expectedPriceCents
                    )
                )
                _actionState.value = UiState.Success(summary)
            } catch (t: Throwable) {
                _actionState.value =UiState.Error(t.message ?: "Erreur inconnue")
            }
        }
    }

    private fun observeReservation() {
        viewModelScope.launch {
            observeReservationsUS()
                .catch { e ->
                    _reservationState.value = UiState.Error("Erreur de chargement: ${e.localizedMessage}")
                }
                .onEach { list : List<Reservation> ->
                    _reservationState.value = if (list.isEmpty()) {
                        UiState.Empty
                    } else {
                        UiState.Success(list)
                    }
                }
                .collect()
        }
    }
}