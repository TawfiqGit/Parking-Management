package com.tawfiqdev.parkingmanagement.presentation.booking.viewmodel

import androidx.lifecycle.ViewModel
import com.tawfiqdev.model.Booking
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor() : ViewModel()   {

    private val _bookings = mutableListOf<Booking>()
    val bookings: List<Booking> = _bookings

}