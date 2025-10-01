package com.tawfiqdev.parkingmanagement.presentation.booking.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawfiqdev.model.EntryExitLog
import com.tawfiqdev.model.Payment
import com.tawfiqdev.model.ReservationDetails
import com.tawfiqdev.parkingmanagement.presentation.utils.UiState
import com.tawfiqdev.parkingmanagement.presentation.utils.UiState.*
import com.tawfiqdev.usecase.AddPaymentUseCase
import com.tawfiqdev.usecase.CancelReservationUseCase
import com.tawfiqdev.usecase.CreateOrUpdateReservationUseCase
import com.tawfiqdev.usecase.GetReservationDetailsUseCase
import com.tawfiqdev.usecase.ObserveReservationDetailsUseCase
import com.tawfiqdev.usecase.ObserveUserReservationsUseCase
import com.tawfiqdev.usecase.SetEntryExitLogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val observeReservationDetailsUseCase: ObserveReservationDetailsUseCase,
    private val getReservationDetailsUseCase: GetReservationDetailsUseCase,
    private val createOrUpdateReservationUseCase: CreateOrUpdateReservationUseCase,
    private val cancelReservationUseCase: CancelReservationUseCase,
    private val addPaymentUseCase: AddPaymentUseCase,
    private val setEntryExitLogUseCase: SetEntryExitLogUseCase,
    private val observeUserReservationsUseCase: ObserveUserReservationsUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val io: CoroutineDispatcher
) : ViewModel() {
    private var reservationId: Long?
        get() = savedStateHandle.get<Long>("reservationId")
        set(value) {
            savedStateHandle["reservationId"] = value
        }

    private val _ui = MutableStateFlow<UiState>(Loading)
    val ui: StateFlow<UiState> = _ui.asStateFlow()
    private val userId = MutableStateFlow(0L)

    @OptIn(ExperimentalCoroutinesApi::class)
    val userReservations: StateFlow<List<ReservationDetails>> =
        userId.flatMapLatest { uid ->
            if (uid <= 0) {
                flowOf(emptyList())
            } else {
                observeUserReservationsUseCase(uid)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    init {
        reservationId?.let {
            observe(it)
        }
    }

    fun setUserId(id: Long) {
        if (id != userId.value) {
            userId.value = id
        }
    }

    private fun observe(id: Long) {
        viewModelScope.launch {
            observeReservationDetailsUseCase(id)
                .onStart {
                    Log.i(TAG, "observe($id): onStart")
                    _ui.value = Loading
                }
                .catch { e ->
                    Log.e(TAG, "observe($id): error", e)
                    _ui.value = Error(e.localizedMessage ?: "Erreur")
                }
                .collect { details ->
                    Log.i(TAG, "observe($id): details=${details}")
                    _ui.value = when (details) {
                        null -> Empty
                        else -> Success(details)
                    }
                }
        }
    }

    fun refresh() {
        val id = reservationId ?: return
        viewModelScope.launch {
            runCatching {
                withContext(io) {
                    getReservationDetailsUseCase(id)
                }
            }.onSuccess { details ->
                _ui.value = when (details) {
                    null -> Empty
                    else -> Success(details)
                }
            }.onFailure { e ->
                _ui.value = Error(e.localizedMessage ?: "Erreur")
            }
        }
    }

    fun saveReservation(details: ReservationDetails) {
        viewModelScope.launch {
            runCatching {
                Log.i(TAG, "saveReservation runCatching: ${details.reservation}")
                createOrUpdateReservationUseCase(details.reservation)
            }.onSuccess { id ->
                Log.i(TAG, "saveReservation: id=$id")
                Log.i(TAG, "saveReservation: reservationId=$reservationId")

                if (reservationId == null) {
                    reservationId = id
                    observe(id)
                } else {
                    refresh()
                }
            }.onFailure { e ->
                Log.e(TAG, "saveReservation: ${e.localizedMessage}")
                _ui.value = UiState.Error(e.localizedMessage ?: "Erreur lors de l’enregistrement")
            }
        }
    }

    fun cancelCurrentReservation() {
        val id = reservationId ?: return
        viewModelScope.launch {
            runCatching { cancelReservationUseCase(id) }
                .onSuccess { refresh() }
                .onFailure { e ->
                    Log.e(TAG, "cancelCurrentReservation: ${e.localizedMessage}")
                    _ui.value = UiState.Error(e.localizedMessage ?: "Erreur lors de l’annulation")
                }
        }
    }

    fun recordPayment(payment: Payment) {
        viewModelScope.launch {
            runCatching { addPaymentUseCase(payment) }
                .onSuccess { refresh() }
                .onFailure { e ->
                    Log.e(TAG, "recordPayment: ${e.localizedMessage}")
                    _ui.value = UiState.Error(e.localizedMessage ?: "Erreur de paiement")
                }
        }
    }

    fun recordEntryExitLog(log: EntryExitLog) {
        viewModelScope.launch {
            runCatching { setEntryExitLogUseCase(log) }
                .onSuccess { refresh() }
                .onFailure { e ->
                    Log.e(TAG, "recordEntryExitLog: ${e.localizedMessage}")
                    _ui.value = UiState.Error(e.localizedMessage ?: "Erreur de suivi")
                }
        }
    }

    companion object {
        private const val TAG = "BookingViewModel"
    }
}