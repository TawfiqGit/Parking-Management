package com.tawfiqdev.parkingmanagement.presentation.booking

import androidx.compose.animation.Crossfade
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tawfiqdev.design_system.components.AppIconAdd
import com.tawfiqdev.design_system.components.AppIconArrowBack
import com.tawfiqdev.design_system.components.AppText
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.enums.BookingStatus
import com.tawfiqdev.model.Booking
import com.tawfiqdev.parkingmanagement.presentation.booking.viewmodel.BookingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    navController: NavController,
    ongoing: List<Booking> = emptyList(),
    completed: List<Booking> = emptyList(),
    cancelled: List<Booking> = emptyList(),
    onBack: () -> Unit = {},
    onRebook: (Booking) -> Unit = {},
    onETicket: (Booking) -> Unit= {}
) {
    var tab by remember { mutableIntStateOf(1) } // 0=Ongoing, 1=Completed (comme la capture), 2=Cancelled
    val tabs = listOf("Ongoing", "Completed", "Cancelled")
    val viewModel = hiltViewModel<BookingViewModel>()

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
                },
                actions = {
                    IconButton(onClick = { /* TODO: action filter */ }) {
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
                selectedTabIndex = tab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor  = AppColor.Black,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[tab]),
                        color = AppColor.GreenRacing,
                        height = 3.dp
                    )
                },
                divider = {}
            ) {
                tabs.forEachIndexed { i, label ->
                    Tab(
                        selected = tab == i,
                        onClick = { tab = i },
                        selectedContentColor   = AppColor.GreenRacing,
                        unselectedContentColor = AppColor.Black.copy(alpha = 0.6f),
                        text = {
                            Text(
                                text = label,
                                fontSize = 14.sp,
                                fontWeight = if (tab == i) FontWeight.SemiBold else FontWeight.Normal
                            )
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            Crossfade(targetState = tab, label = "tabs") { t ->
                when (t) {
                    0 -> BookingList(
                        items = ongoing,
                        purple = AppColor.GreenRacing,
                        showETicket = true,
                        wideRebook = false,
                        onRebook = onRebook,
                        onETicket = onETicket
                    )
                    1 -> BookingList(
                        items = completed,
                        purple = AppColor.GreenRacing,
                        showETicket = true,
                        wideRebook = false,
                        onRebook = onRebook,
                        onETicket = onETicket
                    )
                    2 -> BookingList(
                        items = cancelled,
                        purple = AppColor.GreenRacing,
                        showETicket = false,
                        wideRebook = true,
                        onRebook = onRebook,
                        onETicket = onETicket
                    )
                }
            }
        }
    }
}

@Composable
private fun BookingList(
    items: List<Booking>,
    purple: Color,
    showETicket: Boolean,
    wideRebook: Boolean,
    onRebook: (Booking) -> Unit,
    onETicket: (Booking) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(items, key = { it.id }) { item ->
            BookingCard(
                booking = item,
                highlight = purple,
                showETicket = showETicket,
                wideRebook = wideRebook,
                onRebook = { onRebook(item) },
                onETicket = { onETicket(item) }
            )
        }
    }
}