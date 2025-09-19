package com.tawfiqdev.data.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "spot_type",
    indices = [Index(value = ["code"], unique = true)]
)
data class SpotTypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val code: String,           // STANDARD, PMR, EV, MOTO, XXL…
    val description: String? = null
)