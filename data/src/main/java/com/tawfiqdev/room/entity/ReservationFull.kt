package com.tawfiqdev.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ReservationFull(
    @Embedded val reservation: ReservationEntity,

    @Relation(
        parentColumn = "user_id",
        entityColumn = "id"
    )
    val user: UserEntity,

    @Relation(
        parentColumn = "vehicle_id",
        entityColumn = "id"
    )
    val vehicle: VehicleEntity?,

    @Relation(
        parentColumn = "parking_id",
        entityColumn = "id"
    )
    val parking: ParkingEntity,

    @Relation(
        parentColumn = "spot_id",
        entityColumn = "id"
    )
    val spot: ParkingSpotEntity?,

    @Relation(
        parentColumn = "id",
        entityColumn = "reservation_id"
    )
    val payments: List<PaymentEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "reservation_id"
    )
    val entryExitLog: EntryExitLogEntity?
)