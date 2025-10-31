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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.tawfiqdev.design_system.components.AnimatedSplashScreen
import com.tawfiqdev.model.Parking
import com.tawfiqdev.model.Vehicle
import com.tawfiqdev.parkingmanagement.presentation.booking.BookingScreen
import com.tawfiqdev.parkingmanagement.presentation.history.HistoryPage
import com.tawfiqdev.parkingmanagement.presentation.home.HomeScreen
import com.tawfiqdev.parkingmanagement.presentation.home.component.AddVehicleScreen
import com.tawfiqdev.parkingmanagement.presentation.home.component.SelectLocationScreen
import com.tawfiqdev.parkingmanagement.presentation.home.component.ParkingDetailScreen
import com.tawfiqdev.parkingmanagement.presentation.home.component.SelectVehicleScreen
import com.tawfiqdev.parkingmanagement.presentation.home.viewmodel.BookParkingViewModel
import com.tawfiqdev.parkingmanagement.presentation.setting.SettingScreen
import com.tawfiqdev.parkingmanagement.presentation.splash.MainViewModel
import kotlinx.coroutines.flow.StateFlow
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import kotlin.random.Random

@Composable
fun NavHostScreen(
    isReadyFlow: StateFlow<Boolean>,
) {
    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    val currentRoute = currentDestination?.route
    val mainViewModel = hiltViewModel() as MainViewModel
    val bookParkingViewModel = hiltViewModel() as BookParkingViewModel

    val navItems = listOf(
        NavItem("Home", Icons.Default.Home, Home),
        NavItem("Booking", Icons.Default.DateRange, Booking),
        NavItem("History", Icons.Default.Search, History),
        NavItem("Setting", Icons.Default.Settings, Setting),
    )

    Scaffold(
        bottomBar = {
            if (currentRoute != Splash) {
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
            startDestination = Splash,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Splash) {
                AnimatedSplashScreen(
                    isReadyFlow = isReadyFlow,
                    onFinished = {
                        navController.navigate(Home) {
                            popUpTo(Splash) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(SelectLocation) {
                SelectLocationScreen(
                    onBack = { navController.popBackStack() },
                    onResultClick = { chosen ->
                        Toast.makeText(navController.context, chosen.toString(), Toast.LENGTH_SHORT).show()
                    },
                    onUseCurrentLocation = { /* TODO geoloc */ }
                )
            }
            composable(
                route = "parkingDetail/{parking}",
                arguments = listOf(navArgument("parking") {
                    type = NavType.StringType
                })
            ) { stackEntry ->
                val json = stackEntry.arguments?.getString("parking")
                val decoded = URLDecoder.decode(json, StandardCharsets.UTF_8.toString())
                val parking = Gson().fromJson(decoded, Parking::class.java)

                ParkingDetailScreen(
                    parking = parking,
                    onBackClick = { navController.popBackStack() },
                    onBookClick = { navController.navigate(SelectVehicle) }
                )
            }
            composable(SelectVehicle) {
                SelectVehicleScreen (
                    vehicles = bookParkingViewModel.listVehicle,
                    onBackClick = { navController.popBackStack() },
                    onContinueClick = { navController.navigate(Home) },
                    onAddClick = { navController.navigate(AddVehicle) },
                )
            }
            composable(AddVehicle) {
                AddVehicleScreen(
                    onBackClick = { navController.popBackStack() },
                    onAddVehicle = { brand, model, plate ->
                        bookParkingViewModel.insertVehicle(vehicle =
                            Vehicle(
                                id = 0L,
                                userId = Random.nextLong(500),
                                brand = brand,
                                model = model,
                                registrationPlate = plate,
                                color = null
                            )
                        )
                        navController.popBackStack()
                    }
                )
            }
            composable(Home) { HomeScreen(navController = navController) }
            composable(Booking) { BookingScreen(navController = navController) }
            composable(History) { HistoryPage() }
            composable(Setting) { SettingScreen (navController = navController) }
        }
    }
}