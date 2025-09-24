package com.tawfiqdev.converts

import androidx.room.TypeConverter
import com.tawfiqdev.enums.Category
import com.tawfiqdev.enums.StatusCar
import com.tawfiqdev.enums.StatusRez
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

    // StatusCar -> String
    @TypeConverter
    fun categoryToString(value: Category?): String? = value?.name

    // String -> StatusCar
    @TypeConverter
    fun stringToCategory(value: String?): Category? = value?.let { Category.valueOf(it) }
}