package com.tawfiqdev.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.tawfiqdev.database.entity.UserEntity
import com.tawfiqdev.database.entity.VehicleEntity

data class UserWithVehicles(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val vehicles: List<VehicleEntity>
)