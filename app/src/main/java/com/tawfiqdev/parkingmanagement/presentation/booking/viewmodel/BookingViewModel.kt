package com.tawfiqdev.parkingmanagement.presentation.booking.viewmodel

import androidx.lifecycle.ViewModel
import com.tawfiqdev.domain.usecase.ObservePopularParkingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val observePopularParking: ObservePopularParkingUseCase
) : ViewModel()   {

}