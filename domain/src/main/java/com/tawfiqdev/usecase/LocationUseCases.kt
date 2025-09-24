package com.tawfiqdev.usecase

import com.tawfiqdev.repository.LocationRepository

class SuggestionsUseCase(private val repo: LocationRepository) {
    suspend operator fun invoke(query: String, limit: Int = 10) = repo.suggestions(query,limit)
}

class ResultsUseCase(private val repo: LocationRepository) {
    suspend operator fun invoke(query: String, limit: Int = 20) = repo.results(query,limit)
}

class RecentsUseCase(private val repo: LocationRepository) {
    suspend operator fun invoke(limit: Int = 10) = repo.recents(limit)
}

class MarkAsRecentUseCase(private val repo: LocationRepository) {
    suspend operator fun invoke(id: Int) = repo.markAsRecent(id)
}

class SeedIfEmptyUseCase(private val repo: LocationRepository) {
    suspend operator fun invoke() = repo.seedIfEmpty()
}