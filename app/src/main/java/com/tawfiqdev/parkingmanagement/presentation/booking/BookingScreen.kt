package com.tawfiqdev.parkingmanagement.presentation.booking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tawfiqdev.design_system.components.AppButton
import com.tawfiqdev.design_system.components.AppIconArrowBack
import com.tawfiqdev.design_system.components.AppOutlinedButton
import com.tawfiqdev.design_system.components.AppText
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.design_system.theme.MediumRoundedCornerShape
import com.tawfiqdev.design_system.theme.NormalRoundedCornerShape
import com.tawfiqdev.design_system.theme.SmallLargeRoundedCornerShape
import com.tawfiqdev.parkingmanagement.domain.model.Booking
import com.tawfiqdev.parkingmanagement.presentation.booking.viewmodel.BookingViewModel
import com.tawfiqdev.parkingmanagement.presentation.utils.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    navController: NavController,
    viewModel: BookingViewModel = hiltViewModel()
) {
    var selectedTab by remember { mutableIntStateOf(1) } // 0=Ongoing, 1=Completed, 2=Cancelled
    val tabs = listOf("Ongoing", "Completed", "Cancelled")

    val bookings = remember {
        listOf(
            Booking("ParkWise Ventures", "New York", "USA", "$5.00", 4.9, imageUrl = "https://images.unsplash.com/photo-1483721310020-03333e577078?q=80&w=1200"),
            Booking("AutoNest Spaces", "New York", "USA", "$8.00", 4.8, imageUrl = "https://images.unsplash.com/photo-1532974297617-c0f05fe48bff?q=80&w=1200"),
            Booking("AutoCare Park", "New York", "USA", "$6.00", 4.7, imageUrl = "https://images.unsplash.com/photo-1550355291-bbee04a92027?q=80&w=1200"),
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
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Routes.Home)
                    }) {
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
                containerColor = AppColor.GreenRacing,
                contentColor = AppColor.RoseSpanish,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = AppColor.RoseSeaShell,
                        height = 4.dp
                    )
                }
            ) {
                tabs.forEachIndexed { i, label ->
                    Tab(
                        selected = selectedTab == i,
                        onClick = { selectedTab = i },
                        text = {
                            Text(
                                text = label,
                                color = if (selectedTab == i) AppColor.RoseSeaShell
                                else LocalContentColor.current.copy(alpha = 0.6f),
                                fontWeight = if (selectedTab == i) FontWeight.SemiBold else FontWeight.Normal
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(bookings) { booking ->
                    BookingCard(
                        booking = booking,
                        onRebook = { /* TODO */ },
                        onETicket = { /* TODO */ }
                    )
                }
            }
        }
    }
}

@Composable
fun BookingCard(
    booking: Booking,
    onRebook: () -> Unit,
    onETicket: () -> Unit
) {
    ElevatedCard(
        shape = SmallLargeRoundedCornerShape,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                //Chargement de l'image
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(booking.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(92.dp)
                        .clip(MediumRoundedCornerShape)
                )

                Spacer(Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    AssistChip(
                        onClick = {

                        },
                        label = {
                            AppText(
                                text = booking.category,
                                color = AppColor.Black
                            )
                        },
                        shape = NormalRoundedCornerShape,
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = AppColor.GreenRacing.copy(alpha = 0.12f),
                            labelColor = AppColor.GreenRacing
                        )
                    )

                    Spacer(Modifier.height(8.dp))

                    AppText(
                        text = booking.title,
                        color = AppColor.Black,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(Modifier.height(6.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = LocalContentColor.current.copy(alpha = 0.6f),
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(Modifier.width(4.dp))

                        AppText(
                            text = "${booking.city} - ${booking.country}",
                            color = AppColor.Black.copy(alpha = 0.7f),
                            fontWeight = FontWeight.Normal
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            AppText(
                                text = booking.pricePerHour,
                                color = AppColor.Black.copy(alpha = 0.7f),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp
                            )
                            Spacer(Modifier.width(4.dp))
                            AppText(
                                text =  "/hr",
                                color = AppColor.Black,
                                fontSize = 20.sp
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = AppColor.GreenRacing,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                text = booking.rating.toString(),
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                AppOutlinedButton(
                    modifier = Modifier.weight(1f),
                    text = "Re-Book",
                    shape = MediumRoundedCornerShape,
                    onClick = onRebook
                )

                AppButton(
                    modifier = Modifier.weight(1f),
                    text = "E-Ticket",
                    shape = MediumRoundedCornerShape,
                    onClick = onETicket
                )
            }
        }
    }
}