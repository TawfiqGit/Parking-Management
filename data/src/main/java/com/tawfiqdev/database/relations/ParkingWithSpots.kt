package com.tawfiqdev.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.tawfiqdev.database.entity.ParkingEntity
import com.tawfiqdev.database.entity.ParkingSpotEntity

/**
 * @Embedded
 * Mets colonnes de cet objet direct dans la table/POJO
 * Pas besoin de refaire un JOIN manuel.
 *
 * @Relation
 * fait une relation entre deux entités en utilisant une clé étrangère
 */
data class ParkingWithSpots(
    @Embedded val parking: ParkingEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "parking_id"
    )
    val spots: List<ParkingSpotEntity>
)