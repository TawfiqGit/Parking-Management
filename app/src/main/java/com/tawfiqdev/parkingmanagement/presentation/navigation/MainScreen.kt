package com.tawfiqdev.parkingmanagement.presentation.navigation

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
import androidx.compose.ui.platform.LocalContext
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
                        Toast.makeText(navController.context, chosen.toString(), Toast.LENGTH_SHORT).show()
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
