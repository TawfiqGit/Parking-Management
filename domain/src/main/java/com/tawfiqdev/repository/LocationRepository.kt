package com.tawfiqdev.repository

import com.tawfiqdev.model.LocationSelection

interface LocationRepository {
    suspend fun suggestions(query: String, limit: Int = 10): List<LocationSelection>
    suspend fun results(query: String, limit: Int = 20): List<LocationSelection>
    suspend fun recents(limit: Int = 10): List<LocationSelection>
    suspend fun markAsRecent(id: Int)
    suspend fun seedIfEmpty(): Int
}