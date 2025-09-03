package com.tawfiqdev.design_system.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.design_system.utils.Baseline4
import com.tawfiqdev.design_system.utils.Baseline5

@Composable
fun SectionHeader(title: String, action: String? = null) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = Baseline5, vertical = Baseline4),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText(
            text = title,
            modifier = Modifier.weight(1f),
            color = AppColor.Black,
            fontSize = 20.sp
        )
        if (action != null)
            AppText(
                text = action,
                color = AppColor.Black
            )
    }
}
