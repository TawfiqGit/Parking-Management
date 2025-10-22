package com.tawfiqdev.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tawfiqdev.design_system.icone.AppIcons
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.design_system.theme.AppColor.RoseSeaShell
import com.tawfiqdev.design_system.theme.AppColor.White
import com.tawfiqdev.design_system.theme.MediumRoundedCornerShape

@Composable
fun ParkingSearch(modifier: Modifier = Modifier) {
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
            focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}


@Composable
fun LocationSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(MediumRoundedCornerShape),
        placeholder = { AppText(text = "Location to select", color = AppColor.Black)},
        leadingIcon = { AppIcon(painter = AppIcons.LocationSelectedIcon, tint = AppColor.GreenRacing) },
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = onClear) {
                    Icon(Icons.Filled.Clear, contentDescription = "Clear")
                }
            }
        },
        singleLine = true,
        shape = MediumRoundedCornerShape,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
            }
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AppColor.GreenRacing,
            unfocusedBorderColor = AppColor.GreenTeal,
            disabledBorderColor = AppColor.GreenTeal,
            errorBorderColor = AppColor.RedDeep
        )
    )
}

@Preview
@Composable
fun SearchParkingPreview() {
    ParkingSearch()
}


@Preview
@Composable
fun SearchLocationPreview() {
    LocationSearchBar(
        value = "Paris",
        onValueChange = {},
        onClear = {},
        onSearch = {}
    )
}
