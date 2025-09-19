package com.tawfiqdev.parkingmanagement.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawfiqdev.domain.model.LocationSelection
import com.tawfiqdev.domain.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SelectLocationViewModel @Inject constructor(
    private val repo: LocationRepository
) : ViewModel() {

    val query = MutableStateFlow("")
    private val _suggestions = MutableStateFlow<List<LocationSelection>>(emptyList())
    val suggestions: StateFlow<List<LocationSelection>> = _suggestions.asStateFlow()

    private val _results = MutableStateFlow<List<LocationSelection>>(emptyList())
    val results: StateFlow<List<LocationSelection>> = _results.asStateFlow()

    init {
        viewModelScope.launch {
            repo.seedIfEmpty()
            loadRecents()
        }

        viewModelScope.launch {
            query.debounce(250).distinctUntilChanged().collect { q ->
                if (q.isBlank()) {
                    _suggestions.value = emptyList()
                    loadRecents()
                } else {
                    _suggestions.value = repo.suggestions(q, 8)
                }
            }
        }
    }

    fun onQueryChange(text: String) {
        query.value = text
    }

    fun onClearQuery() {
        query.value = ""
        _suggestions.value = emptyList()
        viewModelScope.launch {
            loadRecents()
        }
    }

    fun onSearch() = viewModelScope.launch {
        val q = query.value
        _results.value = if (q.isBlank()) {
            repo.recents()
        } else {
            repo.results(q)
        }
    }

    fun onSuggestionClicked(sel: LocationSelection) {
        viewModelScope.launch {
            repo.markAsRecent(sel.id)
            query.value = sel.title
            _results.value = listOf(sel)
        }
    }

    fun loadRecents() = viewModelScope.launch {
        _results.value = repo.recents()
    }
}