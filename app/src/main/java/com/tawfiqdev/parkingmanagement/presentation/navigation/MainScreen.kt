package com.tawfiqdev.parkingmanagement.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tawfiqdev.parkingmanagement.presentation.history.HistoryPage
import com.tawfiqdev.parkingmanagement.presentation.home.HomeScreen
import com.tawfiqdev.parkingmanagement.presentation.home.SelectLocationScreen
import com.tawfiqdev.parkingmanagement.presentation.reservation.ReservationPage
import com.tawfiqdev.parkingmanagement.presentation.setting.SettingPage

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val navItems = listOf(
        NavItem("Home", Icons.Default.Home, "home"),
        NavItem("Reservation", Icons.Default.DateRange, "reservation"),
        NavItem("History", Icons.Default.Search, "history"),
        NavItem("Setting", Icons.Default.Settings, "setting")
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentDestination = navController
                    .currentBackStackEntryAsState().value?.destination

                navItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentDestination?.route == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            //Bottom navigation
            composable("home") {
                HomeScreen(
                    navController = navController,
                )
            }
            composable("selectLocation") {
                SelectLocationScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onResultClick = { chosen ->
                        navController.popBackStack()
                    },
                    onUseCurrentLocation = { /* TODO: geoloc */ }
                )
            }
            composable("reservation") { ReservationPage() }
            composable("history") { HistoryPage() }
            composable("setting") { SettingPage() }
        }
    }
}
