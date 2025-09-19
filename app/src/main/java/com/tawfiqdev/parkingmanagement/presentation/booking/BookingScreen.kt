package com.tawfiqdev.parkingmanagement.presentation.booking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tawfiqdev.design_system.components.AppIconArrowBack
import com.tawfiqdev.design_system.components.AppText
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.domain.model.Booking
import com.tawfiqdev.parkingmanagement.presentation.booking.viewmodel.BookingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    navController: NavController,
    viewModel: BookingViewModel = hiltViewModel()
) {
    var selectedTab by rememberSaveable { mutableIntStateOf(1) }     // 0 = Ongoing, 1 = Completed, 2 = Cancelled
    val tabs = listOf("Ongoing", "Completed", "Cancelled")

    val bookings = remember {
        listOf(
            Booking(
                "ParkWise Ventures",
                "Paris",
                "France",
                "$5.00",
                4.9,
                imageUrl = "https://images.unsplash.com/photo-1483721310020-03333e577078?q=80&w=1200"
            ),
            Booking("AutoNest Spaces", "New York", "USA", "$8.00", 4.8, imageUrl = "https://images.unsplash.com/photo-1532974297617-c0f05fe48bff?q=80&w=1200"),
            Booking("AutoCare Park", "Chennai", "India", "$6.00", 4.7, imageUrl = "https://images.unsplash.com/photo-1550355291-bbee04a92027?q=80&w=1200"),
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    AppText(
                        text = "My Booking",
                        textAlignment = TextAlign.Center,
                        color = AppColor.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        AppIconArrowBack()
                    }
                }
            )
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .padding(paddingValue)
                .fillMaxSize()
        ) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor  = AppColor.Black,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = AppColor.GreenRacing,
                        height = 3.dp
                    )
                },
                divider = {}
            ) {
                tabs.forEachIndexed { i, label ->
                    Tab(
                        selected = selectedTab == i,
                        onClick = { selectedTab = i },
                        selectedContentColor   = AppColor.GreenRacing,
                        unselectedContentColor = AppColor.Black.copy(alpha = 0.6f),
                        text = {
                            Text(
                                text = label,
                                fontSize = 14.sp,
                                fontWeight = if (selectedTab == i) FontWeight.SemiBold else FontWeight.Normal
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(bookings, key = { it.title }) { booking ->
                    BookingCard(
                        booking = booking,
                        onRebook = { /* TODO: action rebook */ },
                        onETicket = { /* TODO: action eticket */ }
                    )
                }
            }
        }
    }
}