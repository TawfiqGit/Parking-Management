package com.tawfiqdev.parkingmanagement.presentation.setting

import android.util.Log
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
    val pendingDesiredDark: Boolean? = null,
    val overrideDark: Boolean? = null,
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
    ) { persistedDark, hasPerm, cur ->
        cur.copy(
            isDark = cur.overrideDark ?: persistedDark,
            hasPermission = hasPerm,
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, Ui())

    fun onDarkToggleRequested(desiredEnabled: Boolean) {
        val s = ui.value
        Log.i("parkingManagementTheme", "s: $s")
        Log.i("parkingManagementTheme", "desiredEnabled: $desiredEnabled")

        if (s.hasPermission) {
            applyDark(desiredEnabled)
        } else {
            internal.update { it.copy(showPermissionDialog = true, pendingDesiredDark = desiredEnabled) }
        }
    }

    fun onPermissionResponse(granted: Boolean) {
        viewModelScope.launch {
            repo.saveDarkPermissionGranted(granted)
        }
        val pending = ui.value.pendingDesiredDark

        if (granted && pending != null){
            applyDark(pending)
        }
        internal.update {
            it.copy(showPermissionDialog = false, pendingDesiredDark = null)
        }
    }

    fun dismissPermissionDialog() {
        internal.update {
            it.copy(showPermissionDialog = false, pendingDesiredDark = null)
        }
    }

    private fun applyDark(enabled: Boolean) {
        internal.update { it.copy(overrideDark = enabled) }
        viewModelScope.launch {
            repo.saveDarkMode(enabled)
            internal.update { state ->
                if (state.overrideDark == enabled) {
                    state.copy(overrideDark = null)
                } else {
                    state
                }
            }
        }
        AppCompatDelegate.setDefaultNightMode(
            if (enabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
    }
}