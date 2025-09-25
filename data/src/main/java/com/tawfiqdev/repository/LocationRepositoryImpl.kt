package com.tawfiqdev.repository

import android.util.Log
import com.tawfiqdev.mapper.toDomain
import com.tawfiqdev.model.LocationSelection
import com.tawfiqdev.database.dao.LocationDao
import com.tawfiqdev.database.entity.LocationEntity
import com.tawfiqdev.database.entity.RecentLocationEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationDao: LocationDao,
    private val io: CoroutineDispatcher
): LocationRepository {

    override suspend fun suggestions(query: String, limit: Int): List<LocationSelection> =
        withContext(io) {
            if (query.isBlank()){
                return@withContext emptyList()
            }
            val fts = query.trim().split("\\s+".toRegex())
                .joinToString(" ") {
                    "$it*"
                }
            Log.i("LocationRepositoryImpl", "suggestions: $fts")
            locationDao.searchFts(limit).map {
                it.toDomain()
            }
        }

    override suspend fun results(query: String, limit: Int): List<LocationSelection> =
        withContext(io) {
            Log.i("LocationRepositoryImpl", "results: $query")
            suggestions(query, limit)
        }

    override suspend fun recents(limit: Int): List<LocationSelection> =
        withContext(io) {
            Log.i("LocationRepositoryImpl", "recents: ${locationDao.getRecents(limit)}")

            locationDao.getRecents(limit).map {
                it.toDomain()
            }
        }

    override suspend fun markAsRecent(id: Int) =
        withContext(io) {
            locationDao.upsertRecent(RecentLocationEntity(locationId = id))
        }

    override suspend fun seedIfEmpty(): Int = withContext(io) {
        val seed = listOf(
            LocationEntity(title = "Maison", address = "99 Avenue Achille Peretti, 92200 Neuilly-sur-Seine", latitude = 48.8848, longitude = 2.2690, isCurrentLocation = true),
            LocationEntity(title = "Bureau", address = "10 Rue de Rivoli, 75001 Paris", latitude = 48.8566, longitude = 2.3522),
            LocationEntity(title = "Salle de sport", address = "45 Boulevard Haussmann, 75009 Paris", latitude = 48.8738, longitude = 2.3316),
            LocationEntity(title = "Supermarché", address = "20 Avenue de l'Opéra, 75001 Paris", latitude = 48.8686, longitude = 2.3320)
        )
        locationDao.upsertAll(seed).size
    }
}