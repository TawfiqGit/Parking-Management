package com.tawfiqdev.parkingmanagement.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawfiqdev.parkingmanagement.domain.model.Vehicle
import com.tawfiqdev.parkingmanagement.domain.usecase.vehicle.FlowAllVehicleUseCase
import com.tawfiqdev.parkingmanagement.domain.usecase.vehicle.InsertVehicleUseCase
import com.tawfiqdev.parkingmanagement.domain.utils.ResultOutput
import com.tawfiqdev.parkingmanagement.presentation.utils.VehiclesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val flowAllVehiclesUseCase: FlowAllVehicleUseCase,
    private val insertVehicleUseCase: InsertVehicleUseCase,
) : ViewModel() {
    private val _vehicleState = MutableStateFlow<VehiclesUiState>(VehiclesUiState.Loading)
    val vehiclesState: StateFlow<VehiclesUiState> = _vehicleState.asStateFlow()

    init {
        observeVehicles()
    }
    private fun observeVehicles() {
        viewModelScope.launch {
            flowAllVehiclesUseCase()
                .onEach { list ->
                    _vehicleState.value = if (list.isEmpty()) {
                        VehiclesUiState.Empty
                    } else {
                        VehiclesUiState.Success(list)
                    }
                }
                .catch {
                    _vehicleState.value = VehiclesUiState.Error("Erreur de chargement")
                }
                .collect { /* no-op */ }
        }
    }

    fun insert(vehicle: Vehicle, onError: (String) -> Unit = {}) {
        viewModelScope.launch {
            when (val r = insertVehicleUseCase(vehicle)) {
                is ResultOutput.Success -> Unit
                is ResultOutput.Failure -> onError(r.error.toString())
                else -> {}
            }
        }
    }
}