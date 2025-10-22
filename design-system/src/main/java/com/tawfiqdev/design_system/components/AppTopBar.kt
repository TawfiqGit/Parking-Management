package com.tawfiqdev.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tawfiqdev.design_system.icone.AppIcons.LocationIcon
import com.tawfiqdev.design_system.icone.AppIcons.NotificationIcon
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.design_system.utils.Baseline1
import com.tawfiqdev.design_system.utils.Baseline2
import com.tawfiqdev.design_system.utils.Baseline5
import com.tawfiqdev.design_system.utils.Baseline6

@Composable
fun LocationHeader(
    location: String,
    onNotificationsClick: () -> Unit = {} ,
    onSelectedLocationClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColor.GreenRacing)
            .padding(horizontal = Baseline5, vertical = Baseline6)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AppIcon(painter = LocationIcon, tint = AppColor.White)

                Spacer(Modifier.width(Baseline2))

                AppText(text = location, color = AppColor.White, onClick = onSelectedLocationClick)

                Spacer(Modifier.width(Baseline1))

                Text("▾", color = AppColor.White)
            }
            SquareActionButton(modifier = Modifier.size(48.dp), onClick = onNotificationsClick ,color = AppColor.GreenTeal, icon = {
                AppIcon(painter = NotificationIcon , tint = AppColor.White)
            })
        }
        Spacer(Modifier.height(Baseline5))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ParkingSearch(
                modifier = Modifier.weight(1f)
            )
            SquareActionButton(modifier = Modifier.size(52.dp),color = AppColor.RoseSeaShell, onClick = {}, icon = {
                AppText(text = "≡", color= AppColor.Black)
            })
        }
    }
}

@Preview
@Composable
fun AppTopBarPreview() {
    LocationHeader(location = "India")
}