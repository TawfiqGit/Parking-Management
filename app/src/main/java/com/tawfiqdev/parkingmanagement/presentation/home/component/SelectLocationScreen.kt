package com.tawfiqdev.parkingmanagement.presentation.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tawfiqdev.design_system.components.AppIcon
import com.tawfiqdev.design_system.components.AppText
import com.tawfiqdev.design_system.components.LocationSearchBar
import com.tawfiqdev.design_system.components.SearchResultRow
import com.tawfiqdev.design_system.components.SearchSectionHeader
import com.tawfiqdev.design_system.icone.AppIcons
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.design_system.theme.NormalRoundedCornerShape
import com.tawfiqdev.design_system.utils.Baseline3
import com.tawfiqdev.design_system.utils.Baseline4
import com.tawfiqdev.design_system.utils.Baseline5
import com.tawfiqdev.model.LocationSelection
import com.tawfiqdev.parkingmanagement.presentation.home.viewmodel.SelectLocationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectLocationScreen(
    viewModel: SelectLocationViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onUseCurrentLocation: () -> Unit,
    onResultClick: (LocationSelection) -> Unit
) {
    val query by viewModel.query.collectAsStateWithLifecycle()
    val suggestions by viewModel.suggestions.collectAsStateWithLifecycle()
    val results by viewModel.results.collectAsStateWithLifecycle()

    var showSuggestions by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = { AppText(text = "Enter Your Location", fontSize = 18.sp, color = AppColor.Black) }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = Baseline5)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = Baseline3),
                verticalArrangement = Arrangement.spacedBy(Baseline3)
            ) {
                item {
                    LocationSearchBar(
                        value = query,
                        onValueChange = {
                            viewModel.onQueryChange(it)
                            showSuggestions = it.isNotBlank()
                        },
                        onClear = {
                            viewModel.onClearQuery()
                            showSuggestions = false
                        },
                        onSearch = {
                            viewModel.onSearch()
                            showSuggestions = false
                            focusManager.clearFocus()
                            keyboard?.hide()
                        }
                    )
                }
                item {
                    UseCurrentLocationRow(onClick = onUseCurrentLocation)
                }
                item {
                    SearchSectionHeader(title = "SEARCH RESULT")
                }

                if (results.isNotEmpty()) {
                    items(results, key = {it.id }) { ls ->
                        SearchResultRow(
                            title = ls.title,
                            subtitle = ls.address,
                            onClick = { onResultClick(ls) }
                        )
                    }
                } else {
                    item {
                        Text(
                            "Aucun résultat",
                            color = Color(0xFF6B7280),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        )
                    }
                }
            }

            AnimatedVisibility(visible = showSuggestions && suggestions.isNotEmpty()) {
                SuggestionsPanel(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 72.dp)
                        .align(Alignment.TopStart),
                    suggestions = suggestions,
                    onSuggestionClick = { s ->
                        viewModel.onSuggestionClicked(s)
                        showSuggestions = false
                        focusManager.clearFocus()
                        keyboard?.hide()
                        onResultClick(s)
                    }
                )
            }
        }
    }
}

@Composable
private fun SuggestionsPanel(
    modifier: Modifier = Modifier,
    suggestions: List<LocationSelection>,
    onSuggestionClick: (LocationSelection) -> Unit
) {
    Surface(
        modifier = modifier,
        tonalElevation = 2.dp,
        shape = NormalRoundedCornerShape,
        border = BorderStroke(1.dp, AppColor.GreenRacing)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 320.dp),
            contentPadding = PaddingValues(vertical = 4.dp)
        ) {
            items(suggestions, key = { it.id }) { s ->
                SuggestionRow(
                    title = s.title,
                    subtitle = s.address,
                    onClick = { onSuggestionClick(s) }
                )
                Divider()
            }
        }
    }
}

@Composable
private fun SuggestionRow(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = Baseline4, vertical = Baseline3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIcon(painter = AppIcons.LocationIcon, tint = AppColor.GreenRacing)
        Spacer(Modifier.width(10.dp))
        Column(Modifier.weight(1f)) {
            Text(
                title,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                subtitle,
                style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF6B7280)),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
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