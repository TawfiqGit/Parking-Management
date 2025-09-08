package com.tawfiqdev.parkingmanagement.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawfiqdev.parkingmanagement.domain.model.Parking
import com.tawfiqdev.parkingmanagement.domain.usecase.ObservePopularParkingUseCase
import com.tawfiqdev.parkingmanagement.domain.usecase.SeedParkingIfEmptyUseCase
import com.tawfiqdev.parkingmanagement.domain.utils.ResultOutput
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
class HomeViewModel @Inject constructor(
    private val observePopularParking : ObservePopularParkingUseCase,
    private val seed: SeedParkingIfEmptyUseCase,
) : ViewModel() {

    private val _popularParkingState = MutableStateFlow<UiState>(UiState.Loading)
    val popularParkingState: StateFlow<UiState> = _popularParkingState.asStateFlow()

    init {
        observeParking()
        seedParking()
    }

    private fun observeParking() {
        viewModelScope.launch {
            observePopularParking()
                .catch { e ->
                    _popularParkingState.value = UiState.Error("Erreur de chargement: ${e.localizedMessage}")
                }
                .onEach { list : List<Parking> ->
                    _popularParkingState.value = if (list.isEmpty()) {
                        UiState.Empty
                    } else {
                        UiState.Success(list)
                    }
                }
                .collect()
        }
    }

    fun seedParking() = viewModelScope.launch {
        when (val res = seed()) {
            is ResultOutput.Success -> {
                // OK : res.value = nb insérés (0 si déjà peuplé)
            }
            is ResultOutput.Failure -> {
                // Log/telemetry/UI : res.error (DomainError)
            }
        }
    }
}