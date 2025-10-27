package com.tawfiqdev.parkingmanagement.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.gson.Gson
import com.tawfiqdev.design_system.components.AppIcon
import com.tawfiqdev.design_system.components.AppText
import com.tawfiqdev.design_system.components.LocationHeader
import com.tawfiqdev.design_system.components.RatingBadge
import com.tawfiqdev.design_system.components.SectionHeader
import com.tawfiqdev.design_system.components.SquareActionButton
import com.tawfiqdev.design_system.icone.AppIcons
import com.tawfiqdev.design_system.icone.AppIcons.FavoriteBorder
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.design_system.theme.ExtraMediumRoundedCornerShape
import com.tawfiqdev.design_system.utils.Baseline1
import com.tawfiqdev.design_system.utils.Baseline2
import com.tawfiqdev.design_system.utils.Baseline3
import com.tawfiqdev.design_system.utils.Baseline4
import com.tawfiqdev.design_system.utils.Baseline5
import com.tawfiqdev.model.Parking
import com.tawfiqdev.parkingmanagement.R
import com.tawfiqdev.parkingmanagement.navigation.ParkingDetail
import com.tawfiqdev.parkingmanagement.navigation.SelectLocation
import com.tawfiqdev.parkingmanagement.presentation.home.viewmodel.HomeViewModel
import com.tawfiqdev.parkingmanagement.presentation.utils.UiState
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.popularParkingState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            LocationHeader(
                location = "India",
                onNotificationsClick = {},
                onSelectedLocationClick = { navController.navigate(SelectLocation) },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(vertical = Baseline4),
        ) {
            Spacer(modifier = Modifier.height(Baseline4))

            SectionHeader(title = "Popular Vehicles", action = "See All")

            Spacer(modifier = Modifier.height(Baseline4))

            when (state) {
                is UiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = AppColor.GreenRacing
                        )
                    }
                }

                is UiState.Empty -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AppText(
                            text = "Aucun véhicule",
                            color = AppColor.Black,
                            fontSize = 16.sp,
                            textAlignment = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }

                is UiState.Error -> {
                    val msg = (state as UiState.Error).message
                    Box(modifier = Modifier.fillMaxSize()) {
                        AppText(
                            text = "Erreur: $msg",
                            color = AppColor.Black,
                            fontSize = 16.sp,
                            textAlignment = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }

                is UiState.Success<*> -> {
                    val list = (state as UiState.Success<*>).items as List<*>
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = Baseline5),
                        horizontalArrangement = Arrangement.spacedBy(Baseline4)
                    ) {
                        items(list) { p ->
                            ParkingCard(parking = p as Parking) {
                                val parkingJson = Gson().toJson(p)
                                Log.i("parkingTmo", "HomeScreen ParkingCard encoded $parkingJson")

                                val encoded = URLEncoder.encode(parkingJson, StandardCharsets.UTF_8.toString())
                                navController.navigate("$ParkingDetail/$encoded")
                            }
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ParkingCard(
    parking: Parking ,
    onClick: (Parking) -> Unit = {}
) {
    Card(
        modifier = Modifier.width(260.dp),
        shape = ExtraMediumRoundedCornerShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Column {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clickable { onClick(parking) }
                .background(MaterialTheme.colorScheme.onPrimary)
            ) {
                Image(
                    painter = painterResource(R.drawable.parking_empty),
                    contentDescription = null,
                    modifier = Modifier
                        .matchParentSize()
                        .padding(Baseline2)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                RatingBadge(
                    rating = parking.rating,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(Baseline3)
                )
                SquareActionButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(Baseline3),
                    color = MaterialTheme.colorScheme.onPrimary,
                    onClick = {},
                    icon = {
                        AppIcon(painter = FavoriteBorder, tint = AppColor.RedSpring,)
                    }
                )
            }
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(Baseline4)
            ) {
                AppText(
                    text = parking.category.name,
                    color = AppColor.Black
                )
                Spacer(Modifier.height(Baseline1))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppText(
                        modifier = Modifier.weight(1f),
                        text = parking.name,
                        color = AppColor.Black
                    )
                    AppText(
                        text = String.format("€%.2f", parking.pricePerHour) + "/hr",
                        color = AppColor.Black,
                    )
                }
                Spacer(Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(Baseline5)) {
                    AssistChip(onClick = {}, label = {
                        AppText(
                            text = "${parking.distanceMins} Mins",
                            color = AppColor.Black
                        )},
                        leadingIcon = {
                            AppIcon(painter = AppIcons.ClockIcon, tint = AppColor.GreenRacing)
                        }
                    )
                    AssistChip(onClick = {}, label = {
                        AppText(
                            text = "${parking.spots} Spots",
                            color = AppColor.Black
                        )},
                        leadingIcon = {
                            AppIcon(painter = AppIcons.CarIcon, tint = AppColor.GreenRacing)
                        }
                    )
                }
            }
        }
    }
}