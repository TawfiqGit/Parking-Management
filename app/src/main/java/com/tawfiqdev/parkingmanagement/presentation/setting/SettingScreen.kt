package com.tawfiqdev.parkingmanagement.presentation.setting

import android.content.res.Configuration
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.tawfiqdev.design_system.components.AppIcon
import com.tawfiqdev.design_system.components.AppIconArrowBack
import com.tawfiqdev.design_system.components.AppText
import com.tawfiqdev.design_system.components.HorizontalLineSeparator
import com.tawfiqdev.design_system.icone.AppIcons
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.design_system.theme.MediumRoundedCornerShape
import com.tawfiqdev.design_system.theme.ParkingManagementTheme
import com.tawfiqdev.design_system.utils.Baseline0
import com.tawfiqdev.design_system.utils.Baseline4
import com.tawfiqdev.design_system.utils.Baseline4_5
import com.tawfiqdev.design_system.utils.Baseline5

data class ProfileOption(
    val icon: Painter,
    val title: String,
    val onClick: () -> Unit? = {}
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    navController: NavController,
    viewModel: SettingViewModel = hiltViewModel(),
    onEditClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onLocalisationClick: () -> Unit = {},
    onLogOutClick: () -> Unit = {}
) {
    val ui by viewModel.ui.collectAsStateWithLifecycle()
    if (ui.showPermissionDialog) {
        AlertDialog(
            onDismissRequest = {
                viewModel.dismissPermissionDialog()
            },
            title = {
                Text("Autoriser le mode sombre ?")
            },
            text = {
                Text("Souhaitez-vous activer le mode sombre pour améliorer le confort visuel la nuit ?")
            },
            confirmButton = {
                TextButton(onClick = { viewModel.onPermissionResponse(true) }) {
                    Text("Autoriser")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.onPermissionResponse(false) }) {
                    Text("Refuser")
                }
            }
        )
    }

    val items = listOf(
        ProfileOption(AppIcons.ProfileOutlinedIcon, "Your profile", onProfileClick),
        ProfileOption(AppIcons.NotificationIcon, "Notification", onNotificationClick),
        ProfileOption(AppIcons.LanguageIcon, "Language", onLanguageClick),
        ProfileOption(AppIcons.ModeIcon, "Night Mode",),
        ProfileOption(AppIcons.LocationOutlinedIcon, "Localisation", onLocalisationClick),
        ProfileOption(AppIcons.DeleteOutlinedIcon, "Log out", onLogOutClick)
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    AppText(
                        text = "Setting",
                        textAlignment = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        AppIconArrowBack()
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(Baseline5)
                .background(MaterialTheme.colorScheme.background)
        ) {
            ProfileHeader(onEditClick = onEditClick)
            Spacer(Modifier.height(24.dp))
            ProfileOptionsCard(
                options = items,
                isDark = ui.isDark,
                onToggleDark = { it ->
                    viewModel.onDarkToggleRequested(it)
                }
            )
        }
    }
}

@Composable
private fun ProfileHeader(onEditClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MediumRoundedCornerShape)
            .background(AppColor.GreenTeal)
            .padding(Baseline5)
    ) {
        Box {
            Image(
                painter = AppIcons.ProfileIcon,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(72.dp)
                    .border(width = Baseline0, color = AppColor.White, shape = CircleShape)
            )
            FloatingActionButton(
                onClick = onEditClick,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(28.dp),
                containerColor = AppColor.GreenRacing,
                contentColor = AppColor.White,
                elevation = FloatingActionButtonDefaults.elevation(1.dp)
            ) {
                AppIcon(
                    painter = AppIcons.EditIcon,
                    tint = AppColor.White,
                    modifier = Modifier.size(Baseline5),
                )
            }
        }
        Spacer(Modifier.width(Baseline5))

        Column(verticalArrangement = Arrangement.Center) {
            Text("Esther Howard", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
            Text("esther@example.com", style = MaterialTheme.typography.bodySmall, color = AppColor.White)
        }
    }
}

@Composable
private fun ProfileOptionsCard(
    options: List<ProfileOption>,
    isDark: Boolean,
    onToggleDark: (Boolean) -> Unit
) {
    Card(
        shape = MediumRoundedCornerShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface,),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            options.forEachIndexed { index, option ->
                val isNight = option.title == "Night Mode"
                ProfileRow(
                    option = option,
                    isSwitch = isNight,
                    isChecked = if (isNight) isDark else false,
                    onCheckedChange = { checked ->
                        if (isNight) onToggleDark(checked) else option.onClick()
                    }
                )

                if (index != options.lastIndex) {
                    HorizontalLineSeparator(color = AppColor.Grey)
                    Spacer(Modifier.width(Baseline4))
                }
            }
        }
    }
}

@Composable
fun ProfileRow(
    option: ProfileOption,
    isSwitch: Boolean = false,
    isChecked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (isSwitch) onCheckedChange(!isChecked) else option.onClick()
            }
            .padding(
                horizontal = Baseline5,
                vertical = Baseline4_5
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AppIcon(painter = option.icon,)
        Spacer(modifier = Modifier.width(Baseline5))
        AppText(
            text = option.title,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )

        if (isSwitch){
            Switch(
                checked = isChecked,
                onCheckedChange = onCheckedChange ,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = AppColor.GreenRacing,
                    checkedTrackColor = AppColor.GreenTeal,
                    uncheckedThumbColor = AppColor.GreenTeal,
                    uncheckedTrackColor = AppColor.GreyLight,
                ),
                thumbContent = if (isChecked) {
                    {
                        AppIcon(
                            painter = AppIcons.CheckedIcon,
                            tint = AppColor.White,
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    }
                } else null
            )
        }else{
            AppIcon(
                painter = AppIcons.ArrowRight,
                tint= AppColor.GreyLight
            )
        }
    }
}

@Composable
private fun ProfileRowPreviewContent() {
    var checked by remember {
        mutableStateOf(false)
    }
    Column(Modifier.padding(16.dp)) {
        ProfileRow(
            option = ProfileOption(icon = AppIcons.ModeIcon, title = "Night Mode"),
            isSwitch = true,
            isChecked = checked,
            onCheckedChange = { checked = it }
        )
        ProfileRow(
            option = ProfileOption(icon = AppIcons.LanguageIcon, title = "Language"),
            isSwitch = false
        )
    }
}

@Preview(name = "ProfileRow Light", showBackground = true)
@Composable
fun ProfileRowPreviewLight() {
    ParkingManagementTheme(darkTheme = false) {
        ProfileRowPreviewContent()
    }
}

@Preview(
    name = "ProfileRow Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ProfileRowPreviewDark() {
    ParkingManagementTheme(darkTheme = true) {
        ProfileRowPreviewContent()
    }
}
