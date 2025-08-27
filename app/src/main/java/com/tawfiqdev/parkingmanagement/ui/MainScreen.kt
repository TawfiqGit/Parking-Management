package com.tawfiqdev.parkingmanagement.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tawfiqdev.parkingmanagement.ui.page.HistoryPage
import com.tawfiqdev.parkingmanagement.ui.page.HomePage
import com.tawfiqdev.parkingmanagement.ui.page.ReservationPage
import com.tawfiqdev.parkingmanagement.ui.page.SettingPage

@Composable
fun MainScreen (modifier: Modifier = Modifier){

    val navItem = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Reservation", Icons.Default.DateRange),
        NavItem("History", Icons.Default.Search),
        NavItem("Setting", Icons.Default.Settings)
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        containerColor = Color.White,
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar (
                contentColor = Color.Blue,
                containerColor = Color.White
            ){
                navItem.forEachIndexed{ index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = "Icon")
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ){ innerPadding ->
        ContentScreen(
            modifier = modifier.padding(innerPadding),
            selectedIndex
        )
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex : Int){
    when (selectedIndex){
        0 -> HomePage()
        1 -> ReservationPage()
        2 -> HistoryPage()
        3 -> SettingPage()
    }
}
