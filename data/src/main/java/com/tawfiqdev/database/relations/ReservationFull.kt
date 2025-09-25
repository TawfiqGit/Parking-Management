package com.tawfiqdev.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.tawfiqdev.database.entity.EntryExitLogEntity
import com.tawfiqdev.database.entity.ParkingEntity
import com.tawfiqdev.database.entity.ParkingSpotEntity
import com.tawfiqdev.database.entity.PaymentEntity
import com.tawfiqdev.database.entity.ReservationEntity
import com.tawfiqdev.database.entity.UserEntity
import com.tawfiqdev.database.entity.VehicleEntity

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