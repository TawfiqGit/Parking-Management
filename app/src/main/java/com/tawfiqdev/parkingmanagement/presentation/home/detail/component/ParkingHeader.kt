package com.tawfiqdev.parkingmanagement.presentation.home.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ParkingHeader(
    onBackClick: () -> Unit,
    onBookClick: () -> Unit
){
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.onPrimary,
        tonalElevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Total Price", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface)
                Text(
                    "$5.00 /hr",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Button(
                onClick = onBookClick,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.height(50.dp)
            ) {
                Text("Book Slot", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}