package com.tawfiqdev.design_system.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.tawfiqdev.design_system.theme.AppColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String = "",
    titleSize: TextUnit = 20.sp,
    backgroundColor: Color = AppColor.GreenRacing,
    contentColor: Color = AppColor.White,
    onMenuClick: () -> Unit,
    onBackNavigation: (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            AppText(
                text = title,
                color = contentColor,
                fontSize = titleSize,
                textAlignment = TextAlign.Center,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor,
            titleContentColor = contentColor,
            navigationIconContentColor = contentColor,
            actionIconContentColor = contentColor
        ),
        actions = {
            IconButton(onClick = onMenuClick) {
                AppIcon(
                    painter = rememberVectorPainter(image = Icons.Filled.Menu),
                    tint = contentColor
                )
            }
        },
        navigationIcon = {
            onBackNavigation?.let {
                IconButton(onClick = it) {
                    AppIcon(
                        painter = rememberVectorPainter(image = Icons.Filled.ArrowBack),
                        tint = contentColor
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun AppTopBarPreview() {
    AppTopBar(title = "Test", onMenuClick = {})
}