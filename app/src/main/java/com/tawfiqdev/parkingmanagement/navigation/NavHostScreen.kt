package com.tawfiqdev.parkingmanagement.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tawfiqdev.design_system.components.AnimatedSplashScreen
import com.tawfiqdev.parkingmanagement.presentation.history.HistoryPage
import com.tawfiqdev.parkingmanagement.presentation.home.HomeScreen
import com.tawfiqdev.parkingmanagement.presentation.home.SelectLocationScreen
import com.tawfiqdev.parkingmanagement.presentation.booking.BookingScreen
import com.tawfiqdev.parkingmanagement.presentation.setting.SettingScreen
import com.tawfiqdev.parkingmanagement.presentation.splash.MainViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun NavHostScreen(
    isReadyFlow: StateFlow<Boolean>
) {
    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    val currentRoute = currentDestination?.route
    val viewModel = hiltViewModel() as MainViewModel

    val navItems = listOf(
        NavItem("Home", Icons.Default.Home, Routes.Home),
        NavItem("Booking", Icons.Default.DateRange, Routes.Booking),
        NavItem("History", Icons.Default.Search, Routes.History),
        NavItem("Setting", Icons.Default.Settings, Routes.Setting)
    )

    Scaffold(
        bottomBar = {
            if (currentRoute != Routes.Splash) {
                NavigationBar {
                    navItems.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            onClick = {
                                navController.navigate(item.route) {
                                    //Evite les doublons, restaure l'état
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Splash,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.Splash) {
                AnimatedSplashScreen(
                    isReadyFlow = isReadyFlow,
                    onFinished = {
                        navController.navigate(Routes.Home) {
                            popUpTo(Routes.Splash) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(Routes.Home) { HomeScreen(navController = navController) }
            composable(Routes.Booking) { BookingScreen(navController = navController) }
            composable(Routes.History) { HistoryPage() }
            composable(Routes.Setting) { SettingScreen (navController = navController) }

            composable(Routes.SelectLocation) {
                SelectLocationScreen(
                    onBack = { navController.popBackStack() },
                    onResultClick = { chosen ->
                        Toast.makeText(navController.context, chosen.toString(), Toast.LENGTH_SHORT).show()
                    },
                    onUseCurrentLocation = { /* TODO geoloc */ }
                )
            }
        }
    }
}
