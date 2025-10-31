package com.tawfiqdev.parkingmanagement.presentation.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tawfiqdev.design_system.components.ContinueButton
import com.tawfiqdev.design_system.components.TopBar
import com.tawfiqdev.design_system.theme.SmallMediumRoundedCornerShape
import com.tawfiqdev.model.Vehicle

@Composable
fun SelectVehicleScreen(
    vehicles: List<Vehicle> ,
    onBackClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onContinueClick: () -> Unit = {}
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopBar(
                title = "Select Vehicle",
                onBackClick = onBackClick,
                onAddClick = onAddClick
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                ContinueButton (
                    text = "Continue",
                    onClick = {}
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(vehicles) { index, vehicle ->
                VehicleItem(
                    vehicle = vehicle,
                    isSelected = index == selectedIndex,
                    onClick = { selectedIndex = index }
                )
            }
        }
    }
}

@Composable
fun VehicleItem(vehicle: Vehicle, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = SmallMediumRoundedCornerShape,
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = Color.Gray
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(vehicle.model.orEmpty(), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(vehicle.brand.orEmpty() , fontSize = 12.sp, color = Color.Gray)
                Text(vehicle.registrationPlate, fontSize = 12.sp, color = Color.Gray)
            }

            Spacer(modifier = Modifier.weight(1f))

            RadioButton(
                selected = isSelected,
                onClick = onClick,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}