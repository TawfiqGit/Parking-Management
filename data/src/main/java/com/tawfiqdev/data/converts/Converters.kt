package com.tawfiqdev.data.converts

import androidx.room.TypeConverter
import com.tawfiqdev.domain.enums.Category
import com.tawfiqdev.domain.enums.OccupancySource
import com.tawfiqdev.domain.enums.OccupancyState
import com.tawfiqdev.domain.enums.StatusCar
import com.tawfiqdev.domain.enums.ReservationStatus
import java.time.Instant

class Converters {
    // Instant <-> EpochMillis
    @TypeConverter fun instantToEpochMillis(value: Instant) : Long = value.toEpochMilli()
    @TypeConverter fun epochMillisToInstant(value: Long?): Instant? = value?.let { Instant.ofEpochMilli(it) }

    // StatusCar <-> String
    @TypeConverter fun vehicleStatusToString(value: StatusCar?): String? = value?.name
    @TypeConverter fun stringToVehicleStatus(value: String?): StatusCar? = value?.let { StatusCar.valueOf(it) }

    // Category <-> String
    @TypeConverter fun categoryToString(value: Category?): String? = value?.name
    @TypeConverter fun stringToCategory(value: String?): Category? = value?.let { Category.valueOf(it) }

    // ReservationStatus <-> String
    @TypeConverter fun fromReservationStatus(v: ReservationStatus?): String? = v?.name
    @TypeConverter fun toReservationStatus(v: String?): ReservationStatus? = v?.let { ReservationStatus.valueOf(it) }

    // OccupancyState <-> String
    @TypeConverter fun fromOccupancyState(v: OccupancyState?): String? = v?.name
    @TypeConverter fun toOccupancyState(v: String?): OccupancyState? = v?.let { OccupancyState.valueOf(it) }

    // OccupancySource <-> String
    @TypeConverter fun fromOccupancySource(v: OccupancySource?): String? = v?.name
    @TypeConverter fun toOccupancySource(v: String?): OccupancySource? = v?.let { OccupancySource.valueOf(it) }
}