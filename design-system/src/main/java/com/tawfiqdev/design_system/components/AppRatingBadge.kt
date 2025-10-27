package com.tawfiqdev.design_system.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import com.tawfiqdev.design_system.icone.AppIcons
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.design_system.theme.NormalRoundedCornerShape
import com.tawfiqdev.design_system.utils.Baseline0
import com.tawfiqdev.design_system.utils.Baseline2

@SuppressLint("DefaultLocale")
@Composable
fun RatingBadge(rating: Double, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .shadow(
                elevation = Baseline0,
                shape = NormalRoundedCornerShape
            )
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = NormalRoundedCornerShape
            )
            .padding(
                horizontal = Baseline2,
                vertical = Baseline2
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIcon(
            painter = AppIcons.StartIcon,
            tint = AppColor.YellowCyber
        )
        AppText(
            text = String.format("%.1f", rating),
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}