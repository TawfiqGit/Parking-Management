package com.tawfiqdev.data.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ReservationWithDetails(

    @Embedded val reservation: ReservationEntity,

    @Relation(parentColumn = "vehicle_id", entityColumn = "id")
    val vehicle: VehicleEntity,

    @Relation(parentColumn = "parking_id", entityColumn = "id")
    val parking: ParkingEntity,

    @Relation(parentColumn = "spot_id", entityColumn = "id")
    val spot: ParkingSpotEntity?
)