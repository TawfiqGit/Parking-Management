package com.tawfiqdev.parkingmanagement.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawfiqdev.usecase.SeedParkingIfEmptyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val generateParking : SeedParkingIfEmptyUseCase,

) : ViewModel() {

    init {
        viewModelScope.launch {
            generateParking()
        }
    }



}