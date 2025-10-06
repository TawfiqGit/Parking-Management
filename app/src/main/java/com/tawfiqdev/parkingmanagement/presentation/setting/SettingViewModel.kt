package com.tawfiqdev.parkingmanagement.presentation.setting

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawfiqdev.parkingmanagement.presentation.utils.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Ui(
    val isDark: Boolean = false,
    val hasPermission: Boolean = false,
    val showPermissionDialog: Boolean = false,
    val pendingDesiredDark: Boolean? = null
)

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val repo: PreferencesManager
) : ViewModel() {

    private val internal = MutableStateFlow(Ui())

    val ui: StateFlow<Ui> = combine(
        flow = repo.isDarkModeFlow,
        flow2 = repo.darkPermissionGrantedFlow,
        flow3 = internal
    ) { isDark, hasPerm, cur ->
        cur.copy(isDark = isDark, hasPermission = hasPerm)

    }.stateIn(viewModelScope, SharingStarted.Eagerly, Ui())

    fun onDarkToggleRequested(desiredEnabled: Boolean) {
        val s = ui.value
        if (s.hasPermission) {
            applyDark(desiredEnabled)
        } else {
            internal.update { it.copy(showPermissionDialog = true, pendingDesiredDark = desiredEnabled) }
        }
    }

    fun onPermissionResponse(granted: Boolean) {
        val pending = ui.value.pendingDesiredDark
        viewModelScope.launch { repo.saveDarkPermissionGranted(granted) }
        if (granted && pending != null) applyDark(pending)
        internal.update { it.copy(showPermissionDialog = false, pendingDesiredDark = null) }
    }

    fun dismissPermissionDialog() {
        internal.update {
            it.copy(showPermissionDialog = false, pendingDesiredDark = null)
        }
    }

    private fun applyDark(enabled: Boolean) {
        viewModelScope.launch { repo.saveDarkMode(enabled) }
        AppCompatDelegate.setDefaultNightMode(
            if (enabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
    }
}