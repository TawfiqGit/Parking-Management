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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.tawfiqdev.enums.Category
import com.tawfiqdev.enums.PaymentStatus
import com.tawfiqdev.enums.ReservationStatus
import com.tawfiqdev.enums.SpotType
import com.tawfiqdev.model.EntryExitLog
import com.tawfiqdev.model.Parking
import com.tawfiqdev.model.ParkingSpot
import com.tawfiqdev.model.Payment
import com.tawfiqdev.model.Reservation
import com.tawfiqdev.model.ReservationDetails
import com.tawfiqdev.model.User
import com.tawfiqdev.model.Vehicle
import com.tawfiqdev.parkingmanagement.presentation.booking.component.BookingCard
import com.tawfiqdev.parkingmanagement.presentation.booking.viewmodel.BookingViewModel
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    navController: NavController,
    onBack: () -> Unit = {},
    onRebook: (ReservationDetails) -> Unit = {},
    onETicket: (ReservationDetails) -> Unit= {}
) {
    var tab by remember { mutableIntStateOf(1) } // 0=Ongoing, 1=Completed, 2=Cancelled
    val tabs = listOf("Ongoing", "Completed", "Cancelled")
    val viewModel = hiltViewModel<BookingViewModel>()

    val reservations by viewModel.userReservations.collectAsStateWithLifecycle(emptyList())
    LaunchedEffect(Unit) {
        viewModel.setUserId(1L)
    }

    val ongoing   = remember(reservations) { reservations.filter { it.reservation.status == ReservationStatus.ACTIVE } }
    val completed = remember(reservations) { reservations.filter { it.reservation.status == ReservationStatus.COMPLETED } }
    val cancelled = remember(reservations) { reservations.filter { it.reservation.status == ReservationStatus.CANCELLED } }

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
                    IconButton(onClick = {
                        val baseTime = Instant.parse("2025-09-01T10:00:00Z").toEpochMilli()

                        val completedReservation =
                            fakeDetails(
                                id = 101,
                                status = ReservationStatus.COMPLETED,
                                title = "Parking Opéra",
                                start = baseTime,
                                end = baseTime + 3 * 60 * 60 * 1000,
                                amount = 12.5
                            )

                        val cancelledReservation =
                            fakeDetails(
                                id = 202,
                                status = ReservationStatus.CANCELLED,
                                title = "Parking République",
                                start = baseTime + 86_400_000L,
                                end = baseTime + 86_400_000L + 60 * 60 * 1000,
                                amount = 0.0
                            )
                        viewModel.saveReservation(completedReservation)
                        viewModel.saveReservation(cancelledReservation)
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
                        showETicket = true,
                        wideRebook = false,
                        onRebook = onRebook,
                        onETicket = onETicket
                    )
                    1 -> BookingList(
                        items = completed,
                        showETicket = true,
                        wideRebook = false,
                        onRebook = onRebook,
                        onETicket = onETicket
                    )
                    2 -> BookingList(
                        items = cancelled,
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
    items: List<ReservationDetails>,
    showETicket: Boolean,
    wideRebook: Boolean,
    onRebook: (ReservationDetails) -> Unit,
    onETicket: (ReservationDetails) -> Unit
) {
    if (items.isEmpty()) {
        EmptyState()
        return
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(items, key = { item -> item.reservation.id }) { item ->
            BookingCard(
                reservationDetails = item,
                highlight = AppColor.GreenRacing,
                showETicket = showETicket,
                wideRebook = wideRebook,
                onRebook = { onRebook(item) },
                onETicket = { onETicket(item) }
            )
        }
    }
}

@Composable
private fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(12.dp))
        Text(
            "Aucune réservation à afficher",
            fontSize = 14.sp,
            color = AppColor.Black.copy(0.7f)
        )
    }
}

private fun fakeDetails(
    id: Long,
    status: ReservationStatus,
    title: String,
    start: Long,
    end: Long,
    amount: Double
): ReservationDetails {
    val res = Reservation(
        id = id,
        status = status,
        userId = 1,
        vehicleId = 1,
        parkingId = 10,
        spotId = 55,
        startTime = start,
        endTime = end,
        totalAmount = amount,
        createdAt = start
    )
    val user = User(
        id = 1, name = "Tawfiq", email = "tawfiq@example.com",
        phone = "010290038"
    )
    val vehicle = Vehicle(id = 1, registrationPlate = "AA-123-BB", brand = "Renault", model = "Kadjar", userId = 1, color = "Vert")
    val parkingOpera = Parking(
        id = 1,
        name = "Parking Opéra",
        category = Category.CAR,
        pricePerHour = 2.5,
        rating = 4.3,
        distanceMins = 5,
        spots = 120,
        imageUrl = "https://example.com/opera.jpg"
    )
    val spotA1 = ParkingSpot(
        id = 1L,
        parkingId = 1L,
        spotNumber = "A1",
        type = SpotType.STANDARD,
        isActive = true
    )

    val payments = if (status == ReservationStatus.COMPLETED) {
        listOf(
            Payment(
                id = 1,
                reservationId = id,
                amount = amount,
                method = "CB",
                status = PaymentStatus.SUCCEEDED,
                providerRef ="",
                createdAt = Instant.now().toEpochMilli(),
            )
        )
    } else emptyList()
    val log = EntryExitLog(
        id = 1,
        reservationId = id,
        entryTime = start + 5 * 60 * 1000,
        exitTime = if (status == ReservationStatus.COMPLETED) end else null,
        gateName = null
    )

    return ReservationDetails(
        reservation = res,
        user = user,
        vehicle = vehicle,
        parking = parkingOpera,
        spot = spotA1,
        payments = payments,
        entryExitLog = log
    )
}