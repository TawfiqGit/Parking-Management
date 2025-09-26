package com.tawfiqdev.database

import androidx.room.TypeConverter
import com.tawfiqdev.enums.Category
import com.tawfiqdev.enums.PaymentStatus
import com.tawfiqdev.enums.ReservationStatus
import com.tawfiqdev.enums.SpotType
import com.tawfiqdev.enums.StatusCar
import com.tawfiqdev.enums.StatusRez
import java.time.Instant

class Converters {
    // Instant -> EpochMillis
    @TypeConverter
    fun instantToEpochMillis(value: Instant) : Long = value.toEpochMilli()
    @TypeConverter
    fun epochMillisToInstant(value: Long?): Instant? = value?.let { Instant.ofEpochMilli(it) }

    // StatusRez -> String
    @TypeConverter
    fun reservationStatusToString(value: StatusRez?): String? = value?.name
    @TypeConverter
    fun stringToReservationStatus(value: String?): StatusRez? = value?.let { StatusRez.valueOf(it) }

    // StatusCar -> String
    @TypeConverter
    fun vehicleStatusToString(value: StatusCar?): String? = value?.name
    @TypeConverter
    fun stringToVehicleStatus(value: String?): StatusCar? = value?.let { StatusCar.valueOf(it) }

    // StatusCar -> String
    @TypeConverter
    fun categoryToString(value: Category?): String? = value?.name
    @TypeConverter
    fun stringToCategory(value: String?): Category? = value?.let { Category.valueOf(it) }

    @TypeConverter
    fun resStatusToString(v: ReservationStatus?) = v?.name
    @TypeConverter
    fun stringToResStatus(s: String?) = s?.let { ReservationStatus.valueOf(it) }

    @TypeConverter
    fun payStatusToString(v: PaymentStatus?) = v?.name
    @TypeConverter
    fun stringToPayStatus(s: String?) = s?.let { PaymentStatus.valueOf(it) }

    @TypeConverter
    fun spotTypeToString(v: SpotType?) = v?.name
    @TypeConverter
    fun stringToSpotType(s: String?) = s?.let { SpotType.valueOf(it) }
}