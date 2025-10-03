package com.tawfiqdev.design_system.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.tawfiqdev.design_system.theme.AppColor.Black
import com.tawfiqdev.design_system.theme.AppColor.DarkSurface
import com.tawfiqdev.design_system.theme.AppColor.GreenRacing
import com.tawfiqdev.design_system.theme.AppColor.GreenRacing10
import com.tawfiqdev.design_system.theme.AppColor.GreenRacing30
import com.tawfiqdev.design_system.theme.AppColor.GreenRacing40
import com.tawfiqdev.design_system.theme.AppColor.GreenRacing80
import com.tawfiqdev.design_system.theme.AppColor.GreenRacing90
import com.tawfiqdev.design_system.theme.AppColor.GreenTeal
import com.tawfiqdev.design_system.theme.AppColor.GreenTeal80
import com.tawfiqdev.design_system.theme.AppColor.NearBlack
import com.tawfiqdev.design_system.theme.AppColor.White

private val LightColors = lightColorScheme(
    primary = GreenRacing90 ,
    onPrimary = White,
    primaryContainer = GreenRacing40,
    onPrimaryContainer = GreenRacing80,
    secondary = GreenTeal,
    onSecondary = White,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black
)

private val DarkColors = darkColorScheme(
    primary = GreenRacing30,
    onPrimary = Black,
    primaryContainer = GreenRacing10,
    onPrimaryContainer = GreenRacing,
    secondary = GreenTeal80,
    onSecondary = Black,
    background = NearBlack,
    onBackground = White,
    surface = DarkSurface,
    onSurface = White
)

@Composable
fun ParkingManagementTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true, // Active color of Material You sur Android 12+
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme =
        if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (darkTheme) dynamicDarkColorScheme(context)
            else {
                dynamicLightColorScheme(context)
            }
        } else {
            if (darkTheme) DarkColors
            else {
                LightColors
            }
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}