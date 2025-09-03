package com.tawfiqdev.parkingmanagement.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.tawfiqdev.design_system.utils.Baseline3
import com.tawfiqdev.design_system.utils.Baseline4
import com.tawfiqdev.design_system.utils.Baseline5
import com.tawfiqdev.parkingmanagement.R
import com.tawfiqdev.parkingmanagement.domain.model.Vehicle
import com.tawfiqdev.parkingmanagement.presentation.home.viewmodel.HomeViewModel
import com.tawfiqdev.parkingmanagement.presentation.utils.VehiclesUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.vehiclesState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            LocationHeader(location = "India", onNotificationsClick = {})
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
                is VehiclesUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                is VehiclesUiState.Empty -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AppText(
                            text = "Aucun véhicule",
                            color = AppColor.Black,
                            fontSize = 16.sp,
                            textAlignment = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                is VehiclesUiState.Error -> {
                    val msg = (state as VehiclesUiState.Error).message
                    Box(modifier = Modifier.fillMaxSize()) {
                        AppText(
                            text = "Erreur: $msg",
                            fontSize = 16.sp,
                            textAlignment = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                is VehiclesUiState.Success -> {
                    val list = (state as VehiclesUiState.Success).items
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = Baseline5),
                        horizontalArrangement = Arrangement.spacedBy(Baseline4)
                    ) {
                        items(list) { v ->
                            ParkingCard(vehicle = v) {
                                // action clic sur une carte
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
fun ParkingCard(vehicle: Vehicle, onClickVehicle: (Vehicle) -> Unit = {}) {
    Card(
        modifier = Modifier.width(260.dp),
        shape = ExtraMediumRoundedCornerShape
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(140.dp)) {
                Image(
                    painter = painterResource(R.drawable.city_square),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(Modifier
                    .matchParentSize()
                    .background(AppColor.White)
                )
                RatingBadge(
                    rating = 10.00 ,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(Baseline3)
                )
                SquareActionButton(
                    modifier = Modifier.align(Alignment.TopEnd).padding(Baseline3),
                    color = AppColor.White,
                    onClick = {},
                    icon = {
                        AppIcon(painter = FavoriteBorder, tint = AppColor.RedSpring)
                    }
                )
            }
            Column(
                modifier = Modifier.background(AppColor.White).padding(Baseline4)
            ) {
                AppText(
                    text = "Car Parking",
                    color = AppColor.Black
                )
                Spacer(Modifier.height(Baseline1))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppText(
                        modifier = Modifier.weight(1f),
                        text = "Parking Black",
                        color = AppColor.Black
                    )
                    AppText(
                        text = String.format("€%.2f", 10.34) + "/hr",
                        color = AppColor.Black,
                    )
                }
                Spacer(Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(Baseline5)) {
                    AssistChip(onClick = {}, label = {
                        AppText(
                            text = "${5} Mins",
                            color = AppColor.Black
                        )},
                        leadingIcon = {
                            AppIcon(painter = AppIcons.ClockIcon, tint = AppColor.GreenRacing)
                        }
                    )
                    AssistChip(onClick = {}, label = {
                        AppText(
                            text = "${27} Spots",
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

@Preview
@Composable
fun LoadingImageFromDisk() {
    Image(
        painter = painterResource(id = R.drawable.city_square),
        contentDescription = null,
        contentScale = ContentScale.FillWidth
    )
}
