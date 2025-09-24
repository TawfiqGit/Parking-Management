package com.tawfiqdev.parkingmanagement.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawfiqdev.domain.usecase.SeedParkingIfEmptyUseCase
import com.tawfiqdev.domain.usecase.SeedVehicleIfEmptyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val generateParking : SeedParkingIfEmptyUseCase,
    private val generateVehicle: SeedVehicleIfEmptyUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            generateParking()
            generateVehicle()
        }
    }
}