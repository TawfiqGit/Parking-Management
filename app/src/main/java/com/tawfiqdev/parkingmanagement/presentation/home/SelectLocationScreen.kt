package com.tawfiqdev.parkingmanagement.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tawfiqdev.design_system.components.AppText
import com.tawfiqdev.design_system.components.LocationSearchBar
import com.tawfiqdev.design_system.components.SearchResultRow
import com.tawfiqdev.design_system.components.SearchSectionHeader
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.design_system.theme.NormalRoundedCornerShape
import com.tawfiqdev.design_system.utils.Baseline3
import com.tawfiqdev.design_system.utils.Baseline4
import com.tawfiqdev.design_system.utils.Baseline5

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectLocationScreen(
    onBack: () -> Unit,
    onUseCurrentLocation: () -> Unit,
    onResultClick: (String) -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    AppText(
                        text = "Enter Your Location",
                        fontSize = 18.sp,
                        color = AppColor.Black
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = Baseline5)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(Baseline4))

            LocationSearchBar(
                value = query,
                onValueChange = { query = it },
                onClear = { query = "" },
                onSearch = {
                    focusManager.clearFocus()
                    keyboard?.hide()
                }
            )

            Spacer(Modifier.height(Baseline5))

            UseCurrentLocationRow(onClick = onUseCurrentLocation)

            Spacer(Modifier.height(Baseline5))

            SearchSectionHeader(title = "SEARCH RESULT")

            Spacer(Modifier.height(Baseline3))

            SearchResultRow(
                title = "Golden Avenue",
                subtitle = "8502 Preston Rd. Inglewood, CA 90301",
                onClick = {
                    onResultClick("Golden Avenue")
                }
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun UseCurrentLocationRow(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(NormalRoundedCornerShape)
            .clickable {
                onClick()
            }
            .padding(horizontal = Baseline3, vertical = Baseline4),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(AppColor.GreenRacing.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Outlined.LocationOn,
                contentDescription = null,
                tint = AppColor.GreenRacing
            )
        }
        Spacer(Modifier.width(Baseline3))

        AppText(text = "Use my current location", fontSize = 16.sp, color = AppColor.Black)
    }
}