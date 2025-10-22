package com.tawfiqdev.design_system.theme

import android.os.Build
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.tawfiqdev.design_system.theme.AppColor.Black
import com.tawfiqdev.design_system.theme.AppColor.GreenRacing40
import com.tawfiqdev.design_system.theme.AppColor.GreenRacing80
import com.tawfiqdev.design_system.theme.AppColor.GreenRacing90
import com.tawfiqdev.design_system.theme.AppColor.GreenTeal
import com.tawfiqdev.design_system.theme.AppColor.White

private val LightColors = lightColorScheme(
    // Couleurs de base
    background = AppColor.White,
    surface    = AppColor.White,
    primary    = AppColor.GreenTeal,         // couleur d’accent
    onPrimary  = AppColor.White,             // contraste sur primary
    secondary  = AppColor.GreenRacing,       // 2e accent
    onSecondary = AppColor.White,

    // Couleurs de contenu
    onBackground = AppColor.NearBlack,
    onSurface    = AppColor.NearBlack,

    // Containers (optionnel, mais utile)
    primaryContainer       = AppColor.GreenTeal80,
    onPrimaryContainer     = AppColor.White,
    secondaryContainer     = AppColor.GreenRacing80,
    onSecondaryContainer   = AppColor.NearBlack,
)

private val DarkColors = darkColorScheme(
    // Bases sombres
    background = AppColor.NearBlack,         // 0xFF0B0B0B
    surface    = AppColor.DarkSurface,       // 0xFF121212
    // Accents
    primary    = AppColor.GreenTeal,         // garde l’accent lisible sur fond sombre
    onPrimary  = AppColor.Black,             // ou AppColor.NearBlack si tu préfères
    secondary  = AppColor.GreenRacing10,
    onSecondary = AppColor.White,

    // Contenu clair au-dessus des fonds sombres
    onBackground = AppColor.White,
    onSurface    = AppColor.White,

    // Containers (sombres)
    primaryContainer       = AppColor.GreenTeal80,   // un ton désaturé sombre
    onPrimaryContainer     = AppColor.White,
    secondaryContainer     = AppColor.GreenRacing80,
    onSecondaryContainer   = AppColor.NearBlack,

    // Tertiaire si tu l’utilises (facultatif)
    tertiary    = AppColor.SoftBlue,
    onTertiary  = AppColor.NearBlack,
)

@Composable
fun ParkingManagementTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Active color of Material You sur Android 12+
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme =
        if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        } else {
            if (darkTheme) DarkColors else LightColors
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}