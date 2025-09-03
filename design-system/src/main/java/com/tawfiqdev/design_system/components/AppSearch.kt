package com.tawfiqdev.design_system.components

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.tawfiqdev.design_system.icone.AppIcons
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.design_system.theme.AppColor.RoseSeaShell
import com.tawfiqdev.design_system.theme.AppColor.White
import com.tawfiqdev.design_system.theme.MediumRoundedCornerShape

@Composable
fun AppSearchField(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = { AppIcon(
            painter = AppIcons.SearchIcon,
            tint = AppColor.RoseSpanish
        ) },
        placeholder = { Text("Search") },
        singleLine = true,
        modifier = modifier
            .clip(MediumRoundedCornerShape)
            .background(RoseSeaShell),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            disabledContainerColor = White
        )
    )
}

@Preview
@Composable
fun SearchPreview() {
    AppSearchField()
}
