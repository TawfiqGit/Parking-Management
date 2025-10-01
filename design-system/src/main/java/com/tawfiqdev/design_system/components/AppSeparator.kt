package com.tawfiqdev.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalLineSeparator(
    color: Color = Color.Black,
) {
    Divider(
        thickness = 1.dp,
        color = color
    )
}

@Composable
fun VerticalLineSeparator(
    modifier: Modifier = Modifier,
    color: Color = Color.Black
) {
    Box(
        modifier
            .width(1.dp)
            .fillMaxHeight()
            .background(color)
    )
}