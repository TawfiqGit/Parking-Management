package com.tawfiqdev.parkingmanagement.presentation.home.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tawfiqdev.parkingmanagement.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParkingDetailScreen(
    onBackClick: () -> Unit = {},
    onBookClick: () -> Unit = {}
) {
    var isFavorite by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableStateOf("Gallery") }

    val gallery = listOf(
        R.drawable.parking1,
        R.drawable.parking2,
        R.drawable.parking3,
        R.drawable.parking_empty
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
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
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // --- Header image ---
            Box(modifier = Modifier.height(280.dp)) {
                Image(
                    painter = painterResource(R.drawable.parking1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Back / Share / Like buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.onPrimary)
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }

                    Row {
                        IconButton(
                            onClick = { /* Share */ },
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.onPrimary)
                        ) {
                            Icon(Icons.Outlined.Share, contentDescription = "Share")
                        }

                        IconButton(
                            onClick = { isFavorite = !isFavorite },
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.onPrimary)
                        ) {
                            Icon(
                                if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                // Small gallery preview row
                LazyRow(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .padding(6.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(gallery.take(5)) { img ->
                        Image(
                            painter = painterResource(img),
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                AssistChip(
                    onClick = {},
                    label = { Text("Car Parking") },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        labelColor = MaterialTheme.colorScheme.primary
                    )
                )

                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "⭐ 4.8 (365 reviews)",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Text(
                    "GreenPark Innovations",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    "1012 Ocean avenue, New York, USA",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.outline
                )
            }

            // --- Tabs section ---
            val tabs = listOf("About", "Gallery", "Review")
            TabRow(
                selectedTabIndex = tabs.indexOf(selectedTab),
                containerColor = MaterialTheme.colorScheme.onPrimary
            ) {
                tabs.forEach { tab ->
                    Tab(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        text = {
                            Text(
                                tab,
                                color = if (selectedTab == tab)
                                    MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    )
                }
            }

            when (selectedTab) {
                "Gallery" -> {
                    GallerySection()
                }
                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Content for $selectedTab")
                    }
                }
            }
        }
    }
}

@Composable
fun GallerySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Gallery (400)", fontWeight = FontWeight.Bold)
            Text(
                "add photo",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(Modifier.height(12.dp))

        // Grid of gallery photos
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Image(
                painter = painterResource(R.drawable.parking1),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(R.drawable.parking2),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}
