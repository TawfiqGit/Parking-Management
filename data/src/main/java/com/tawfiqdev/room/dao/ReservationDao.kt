package com.tawfiqdev.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.tawfiqdev.enums.StatusRez
import com.tawfiqdev.room.entity.ParkingSpotEntity
import com.tawfiqdev.room.entity.ReservationEntity
import com.tawfiqdev.room.entity.ReservationFull
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservationDao {

    @Insert
    suspend fun insert(reservationEntity: ReservationEntity) : Long

    @Update
    suspend fun update(reservationEntity: ReservationEntity)

    @Delete
    suspend fun delete(reservationEntity: ReservationEntity)

    @Query("SELECT * FROM reservations")
    fun observeReservation(): Flow<List<ReservationEntity>>

    @Transaction
    @Query("SELECT * FROM reservations WHERE id = :id")
    suspend fun getFullReservationById(id: Long): ReservationFull?

    @Transaction
    @Query("""
        SELECT * FROM reservations 
        WHERE user_id = :userId 
          AND end_time >= :now
        ORDER BY start_time ASC
        LIMIT :limit
    """)
    suspend fun upcomingForUser(userId: Long, now: Long, limit: Int = 20): List<ReservationFull>

    /** Vérifier disponibilités: trouver les places libres sur un créneau */
    @Query("""
        SELECT s.* FROM parking_spots s
        WHERE s.parking_id = :parkingId
          AND s.is_active = 1
          AND s.id NOT IN (
             SELECT spot_id FROM reservations 
             WHERE parking_id = :parkingId
               AND spot_id IS NOT NULL
               AND NOT (end_time <= :startTime OR start_time >= :endTime)
          )
    """)
    suspend fun findFreeSpots(parkingId: Long, startTime: Long, endTime: Long): List<ParkingSpotEntity>

    /** Option: verrouiller une place (TX) pour éviter les chevauchements lors d'une réservation */
    @Transaction
    suspend fun createReservationWithCheck(
        parkingId: Long,
        spotId: Long?,
        userId: Long,
        vehicleId: Long?,
        startTime: Long,
        endTime: Long,
        now: Long
    ): Long {
        // 1) si spot explicite, vérifier qu’il n’y a pas de conflit
        if (spotId != null) {
            val conflicts = countSpotConflicts(parkingId, spotId, startTime, endTime)
            require(conflicts == 0) { "Spot already booked for requested time range" }
        }
        // 2) insert
        return insert(
            ReservationEntity(
                userId = userId,
                vehicleId = vehicleId,
                parkingId = parkingId,
                spotId = spotId,
                startTime = startTime,
                endTime = endTime,
                createdAt = now
            )
        )
    }

    @Query("""
        SELECT COUNT(*) FROM reservations
        WHERE parking_id = :parkingId
          AND spot_id = :spotId
          AND NOT (end_time <= :startTime OR start_time >= :endTime)
    """)
    suspend fun countSpotConflicts(
        parkingId: Long,
        spotId: Long,
        startTime: Long,
        endTime: Long
    ): Int
}