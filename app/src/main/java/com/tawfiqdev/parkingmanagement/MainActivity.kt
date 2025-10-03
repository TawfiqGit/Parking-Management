package com.tawfiqdev.parkingmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.tawfiqdev.design_system.theme.ParkingManagementTheme
import com.tawfiqdev.parkingmanagement.navigation.NavHostScreen
import com.tawfiqdev.parkingmanagement.presentation.splash.SplashViewModel
import com.tawfiqdev.parkingmanagement.presentation.utils.PreferencesManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        val preferencesManager = PreferencesManager(this)

        splashScreen.setKeepOnScreenCondition {
            !splashViewModel.isReady.value
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Flow fonctionne sur des coroutines
        lifecycleScope.launch {
            preferencesManager.isDarkModeFlow.collect {
                setContent {
                    ParkingManagementTheme(darkTheme = it) {
                        NavHostScreen(
                            isReadyFlow = splashViewModel.isReady
                        )
                    }
                }
            }
        }
    }
}