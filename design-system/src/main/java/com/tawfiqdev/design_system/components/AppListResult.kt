package com.tawfiqdev.design_system.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tawfiqdev.design_system.icone.AppIcons
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.design_system.utils.Baseline3
import com.tawfiqdev.design_system.utils.Baseline4
import com.tawfiqdev.design_system.utils.Baseline5

@Composable
fun SearchSectionHeader(title: String) {
    AppText(
        text = title,
        fontSize = 12.sp,
        color = AppColor.GreyDark
    )
}

@Composable
fun SearchResultRow(title: String, subtitle: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = Baseline4)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AppIcon(painter = AppIcons.LocationSelectedIcon, tint = AppColor.GreenRacing)

            Spacer(Modifier.width(Baseline3))

            AppText(text = title, fontSize = 16.sp, color = AppColor.Black)
        }
        Spacer(Modifier.height(2.dp))

        AppText(text = subtitle, fontSize = 14.sp, color = AppColor.GreyDark)

        Spacer(modifier = Modifier.padding(top = Baseline3))
    }
}