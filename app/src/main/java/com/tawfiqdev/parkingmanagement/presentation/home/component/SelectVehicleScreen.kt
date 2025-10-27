package com.tawfiqdev.parkingmanagement.presentation.home.component

import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tawfiqdev.design_system.theme.SmallMediumRoundedCornerShape

@Composable
fun SelectVehicleScreen(
    vehicles: List<Vehicle> = sampleVehicles(),
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
            ContinueButton(
                text = "Continue",
                onClick = onContinueClick
            )
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
fun TopBar(title: String, onBackClick: () -> Unit, onAddClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .border(1.dp, Color.Gray, CircleShape)
                    .clip(CircleShape)
                    .padding(4.dp)
                    .clip(CircleShape)
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        IconButton(onClick = onAddClick,) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                modifier = Modifier
                    .border(1.dp, Color.Gray, CircleShape)
                    .clip(CircleShape)
                    .padding(4.dp)
                    .clip(CircleShape)
            )
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
                Text(vehicle.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(vehicle.details, fontSize = 12.sp, color = Color.Gray)
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

@Composable
fun ContinueButton(text: String, onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            )
        ) {
            Text(text = text, color = Color.White)
        }
    }
}


data class Vehicle(val name: String, val details: String)
fun sampleVehicles() = listOf(
    Vehicle("Toyota Fortuner", "SUV • GR 123-ABCD"),
    Vehicle("Audi", "Sedan • GR 123-ABCDE"),
    Vehicle("Hyundai Verna", "Sedan • GR A12-BCDE"),
    Vehicle("Toyota Innova", "MPV • GR B34-CDEF"),
    Vehicle("Ford Endeavour", "SUV • GR B34-CDDF"),
    Vehicle("Kia Seltos", "SUV • GR C56-DEFG")
)