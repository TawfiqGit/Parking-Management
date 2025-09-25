package com.tawfiqdev.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "entry_exit_logs",
    foreignKeys = [
        ForeignKey(
            entity = ReservationEntity::class,
            parentColumns = ["id"],
            childColumns = ["reservation_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["reservation_id"], unique = true)]
)
data class EntryExitLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "reservation_id") val reservationId: Long,
    @ColumnInfo(name = "entry_time") val entryTime: Long?, // null si pas encore entré
    @ColumnInfo(name = "exit_time") val exitTime: Long?,   // null si pas encore sorti
    @ColumnInfo(name = "gate_name") val gateName: String?
)
