package com.tawfiqdev.parkingmanagement.presentation.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore by preferencesDataStore(name = "prefs_app")

class PreferencesManager @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    private companion object {
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
        val DARK_PERMISSION_GRANTED = booleanPreferencesKey("dark_permission_granted")
    }

    val isDarkModeFlow: Flow<Boolean> =
        context.dataStore.data.map {
            it[DARK_MODE_KEY] ?: false
        }

    val darkPermissionGrantedFlow: Flow<Boolean> =
        context.dataStore.data.map {
            it[DARK_PERMISSION_GRANTED] ?: false
        }

    suspend fun saveDarkMode(enabled: Boolean) {
        context.dataStore.edit {
            it[DARK_MODE_KEY] = enabled
        }
    }

    suspend fun saveDarkPermissionGranted(granted: Boolean) {
        context.dataStore.edit {
            it[DARK_PERMISSION_GRANTED] = granted
        }
    }
}