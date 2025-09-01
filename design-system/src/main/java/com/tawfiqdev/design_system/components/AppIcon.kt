package com.tawfiqdev.design_system.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tawfiqdev.design_system.icone.AppIcons
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.design_system.theme.SmallRoundedCornerShape

@Composable
fun AppIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    size: Dp = 24.dp,
    tint: Color = Color.Unspecified
) {
    Icon(
        modifier = modifier
            .size(size)
            .clip(SmallRoundedCornerShape),
        painter = painter,
        contentDescription = null,
        tint = tint
    )
}

@Preview
@Composable
fun AppIcon() {
    AppIcon(
        size = 80.dp,
        painter = AppIcons.CalendarIcon,
        tint = AppColor.RoseSpanish
    )
}