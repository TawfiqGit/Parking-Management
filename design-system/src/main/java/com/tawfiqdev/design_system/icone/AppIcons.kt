package com.tawfiqdev.design_system.icone

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.tawfiqdev.design_system.R

object AppIcons {
    val ErrorIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.error_icon)

    val AlertIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.alert_icon)

    val CalendarIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.calendar_icon)

    val EventIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.event_icon)

    val ClockIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.clock_icon)

    val DeleteIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.delete_icon)
}