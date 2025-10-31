package com.tawfiqdev.parkingmanagement.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawfiqdev.model.Vehicle
import com.tawfiqdev.usecase.FlowAllVehicleUseCase
import com.tawfiqdev.usecase.InsertVehicleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookParkingViewModel @Inject constructor(
    private val observeVehicleUseCase: FlowAllVehicleUseCase,
    private val insertVehicleUseCase: InsertVehicleUseCase,
) : ViewModel() {

    var listVehicle = emptyList<Vehicle>()

    init {
        observeVehicle()
    }

    private fun observeVehicle(){
        viewModelScope.launch {
            observeVehicleUseCase()
                .catch { e ->
                    Log.e("bookPark", "observeVehicleUseCase: error", e)
                }
                .collect{
                    Log.i("bookPark", "observeVehicleUseCase $it")
                    listVehicle = it
                }
        }
    }

    fun insertVehicle(vehicle: Vehicle) {
        viewModelScope.launch {
            runCatching {
                insertVehicleUseCase(vehicle)
            }.onSuccess { vehicle ->
                Log.i("bookPark", "insertVehicle: vehicleId = $vehicle")
            }.onFailure { e ->
                Log.e("bookPark", "insertVehicle: ${e.localizedMessage}")
            }
        }
    }

}