package com.tawfiqdev.parkingmanagement.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tawfiqdev.design_system.components.AppIcon
import com.tawfiqdev.design_system.components.AppText
import com.tawfiqdev.design_system.components.LocationHeader
import com.tawfiqdev.design_system.icone.AppIcons
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.design_system.theme.NormalRoundedCornerShape
import com.tawfiqdev.design_system.utils.Baseline3
import com.tawfiqdev.design_system.utils.Baseline4
import com.tawfiqdev.design_system.utils.Baseline5
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
                .padding(horizontal = Baseline4),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(Baseline5))

            when (state) {
                is VehiclesUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                is VehiclesUiState.Empty -> {
                    AppText(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "Aucun véhicule",
                        color = AppColor.Black,
                        fontSize = 16.sp ,
                        textAlignment = TextAlign.Center
                    )
                }
                is VehiclesUiState.Error -> {
                    val msg = (state as VehiclesUiState.Error).message
                    AppText(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "Erreur: $msg",
                        fontSize = 16.sp ,
                        textAlignment = TextAlign.Center
                    )
                }
                is VehiclesUiState.Success -> {
                    val list = (state as VehiclesUiState.Success).items
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(list) { v ->
                            VehicleCard(vehicle = v) {

                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VehicleCard(
    vehicle: Vehicle,
    onClick: (Vehicle) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(NormalRoundedCornerShape)
            .clickable {
                onClick(vehicle)
            },
        colors = CardDefaults.cardColors(containerColor = AppColor.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = Baseline3,
                    vertical = Baseline5
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = AppColor.RoseSeaShell,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                AppIcon(
                    modifier = Modifier.padding(Baseline3),
                    painter = AppIcons.VehicleIcon,
                    size = 32.dp
                )
            }
            Spacer(modifier = Modifier.width(Baseline3))

            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppIcon(
                        painter = AppIcons.ClockIcon,
                        size = 16.dp
                    )
                    Spacer(modifier = Modifier.width(Baseline3))
                    AppText(
                        text = vehicle.registrationPlate,
                        fontSize = 16.sp,
                        color = AppColor.Black
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AppIcon(
                        painter = AppIcons.CalendarIcon,
                        size = 16.dp,
                        tint = AppColor.Black
                    )
                    Spacer(modifier = Modifier.width(Baseline3))
                    AppText(
                        text = "${vehicle.marque} - ${vehicle.model}",
                        fontSize = 14.sp ,
                        color = AppColor.Black
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1F))
        }
    }
}
