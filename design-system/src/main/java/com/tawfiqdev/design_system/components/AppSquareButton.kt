package com.tawfiqdev.design_system.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tawfiqdev.design_system.theme.AppColor

@Composable
fun SquareActionButton(
    modifier: Modifier,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    color: Color = MaterialTheme.colorScheme.primary
) {
    FilledTonalIconButton(
        onClick = onClick,
        modifier = modifier,
        colors = IconButtonDefaults.filledTonalIconButtonColors(
            containerColor = color
        )
    ) {
        icon()
    }
}

@Preview
@Composable
fun SquareActionButtonPreview() {
    SquareActionButton(modifier = Modifier.size(52.dp),color = AppColor.RoseSeaShell, onClick = {}, icon = {
        AppText(text = "≡", color= AppColor.Black)
    })
}