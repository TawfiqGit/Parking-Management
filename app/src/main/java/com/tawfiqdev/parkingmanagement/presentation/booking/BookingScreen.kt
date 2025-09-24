package com.tawfiqdev.parkingmanagement.presentation.booking

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.tawfiqdev.design_system.components.AppIconAdd
import com.tawfiqdev.design_system.components.AppIconArrowBack
import com.tawfiqdev.design_system.components.AppText
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.domain.model.ReservationSummary
import com.tawfiqdev.parkingmanagement.presentation.booking.viewmodel.BookingViewModel
import com.tawfiqdev.parkingmanagement.presentation.utils.UiState
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun BookingScreen(
    navController: NavController,
    viewModel: BookingViewModel = hiltViewModel()
) {
    val reservationState by viewModel.reservationState.collectAsStateWithLifecycle()
    val actionState by viewModel.actionState.collectAsStateWithLifecycle()
    var selectedTab by rememberSaveable { mutableIntStateOf(1) }     // 0 = Ongoing, 1 = Completed, 2 = Cancelled
    val tabs = listOf("Ongoing", "Completed", "Cancelled")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    AppText(
                        text = "My Booking",
                        textAlignment = TextAlign.Center,
                        color = AppColor.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        AppIconArrowBack()
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.create(
                            vehicle = 2L,
                            parking = 1L,
                            spot = 0L,
                            start = 10,
                            end = 20L,
                            expectedPriceCents = null
                        )
                    }) {
                        AppIconAdd()
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

            when (reservationState) {
                UiState.Loading -> {}
                is UiState.Idle -> {}
                is UiState.Error -> {
                    val error = (actionState as UiState.Error).message
                    Log.d("BookingScreen", "ReservationState ERROR: $error")
                }
                is UiState.Success<*> -> {
                    val data = (actionState as UiState.Success<*>).items
                    val summary = data as? ReservationSummary

                    if (summary != null) {
                        BookingCard(
                            summary = summary,
                            onRebook = {},
                            onETicket = {}
                        )
                    }
                }
                else -> {}
            }
        }
    }
}