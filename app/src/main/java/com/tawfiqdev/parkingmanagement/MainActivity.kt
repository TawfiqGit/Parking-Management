package com.tawfiqdev.parkingmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.tawfiqdev.design_system.theme.ParkingManagementTheme
import com.tawfiqdev.parkingmanagement.navigation.NavHostScreen
import com.tawfiqdev.parkingmanagement.presentation.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
            !splashViewModel.isReady.value
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParkingManagementTheme {
                NavHostScreen(
                    isReadyFlow = splashViewModel.isReady
                )
            }
        }
    }
}