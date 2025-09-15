package com.tawfiqdev.design_system.icone

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.tawfiqdev.design_system.R

object AppIcons {

    val AppIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.app_icon)

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
        get() = painterResource(id = R.drawable.time_icon)

    val AddIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.add_icon)

    val DeleteIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.delete_icon)

    val VehicleIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.vehicle)

    val LocationIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.map)

    val LocationSelectedIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.location)

    val NotificationIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.notif_icon)

    val FavoriteBorder: Painter
        @Composable
        get() = painterResource(id = R.drawable.outline_favorite)

    val SearchIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.search_icon)

    val CarIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.car_icon)

    val StartIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.star_icon)
}