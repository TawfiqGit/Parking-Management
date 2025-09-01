package com.tawfiqdev.parkingmanagement.data.converts

import androidx.room.TypeConverter
import com.tawfiqdev.parkingmanagement.data.room.entity.StatusRez
import com.tawfiqdev.parkingmanagement.domain.model.StatusCar
import java.time.Instant

class Converters {
    // Instant -> EpochMillis
    @TypeConverter
    fun instantToEpochMillis(value: Instant) : Long = value.toEpochMilli()

    // EpochMillis -> Instance
    @TypeConverter
    fun epochMillisToInstant(value: Long?): Instant? = value?.let { Instant.ofEpochMilli(it) }

    // StatusRez -> String
    @TypeConverter
    fun reservationStatusToString(value: StatusRez?): String? = value?.name

    // String -> StatusRez
    @TypeConverter
    fun stringToReservationStatus(value: String?): StatusRez? = value?.let { StatusRez.valueOf(it) }

    // StatusCar -> String
    @TypeConverter
    fun vehicleStatusToString(value: StatusCar?): String? = value?.name

    // String -> StatusCar
    @TypeConverter
    fun stringToVehicleStatus(value: String?): StatusCar? = value?.let { StatusCar.valueOf(it) }
}