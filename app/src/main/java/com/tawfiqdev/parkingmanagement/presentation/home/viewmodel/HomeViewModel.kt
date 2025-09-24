package com.tawfiqdev.parkingmanagement.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawfiqdev.domain.model.Parking
import com.tawfiqdev.domain.usecase.ObservePopularParkingUseCase
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
    private val observePopularParking: ObservePopularParkingUseCase,
) : ViewModel() {

    private val _popularParkingState = MutableStateFlow<UiState>(UiState.Loading)
    val popularParkingState: StateFlow<UiState> = _popularParkingState.asStateFlow()

    init {
        observeParking()
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
}