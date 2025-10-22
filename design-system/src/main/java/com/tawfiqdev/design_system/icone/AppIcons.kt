package com.tawfiqdev.design_system.icone

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
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

    val ArrowRight: Painter
        @Composable
        get() = rememberVectorPainter(Icons.Outlined.KeyboardArrowRight)

    val EventIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.event_icon)

    val ClockIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.time_icon)

    val AddIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.add_icon)

    val VehicleIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.vehicle)

    val LocationIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.map)

    val LocationSelectedIcon: Painter
        @Composable
        get() = painterResource(id = R.drawable.location)

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

    val ProfileIcon: Painter
        @Composable
        get() = rememberVectorPainter(Icons.Default.Person)

    val ProfileOutlinedIcon: Painter
        @Composable
        get() = rememberVectorPainter(Icons.Outlined.Person)

    val NotificationIcon: Painter
        @Composable
        get() = rememberVectorPainter(Icons.Outlined.Notifications)

    val LanguageIcon: Painter
        @Composable
        get() = painterResource(R.drawable.translate_icon)

    val LocationOutlinedIcon: Painter
        @Composable
        get() = rememberVectorPainter(Icons.Outlined.LocationOn)

    val DeleteOutlinedIcon: Painter
        @Composable
        get() = rememberVectorPainter(Icons.Outlined.Delete)

    val DeleteIcon: Painter
        @Composable
        get() = rememberVectorPainter(Icons.Default.Delete)

    val ModeIcon: Painter
        @Composable
        get() = painterResource(R.drawable.dark_mode_icon)

    val EditIcon: Painter
        @Composable
        get() = rememberVectorPainter(Icons.Default.Edit)

    val CheckedIcon: Painter
        @Composable
        get() = rememberVectorPainter(Icons.Filled.Check)
}