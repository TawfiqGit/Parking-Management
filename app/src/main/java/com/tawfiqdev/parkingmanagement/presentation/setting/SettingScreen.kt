package com.tawfiqdev.parkingmanagement.presentation.setting

import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tawfiqdev.design_system.components.AppIcon
import com.tawfiqdev.design_system.components.AppIconArrowBack
import com.tawfiqdev.design_system.components.AppText
import com.tawfiqdev.design_system.components.HorizontalLineSeparator
import com.tawfiqdev.design_system.icone.AppIcons
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.design_system.theme.MediumRoundedCornerShape
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
    onEditClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onLocalisationClick: () -> Unit = {},
    onLogOutClick: () -> Unit = {}
) {
    val items = listOf(
        ProfileOption(
            icon = AppIcons.ProfileOutlinedIcon,
            title = "Your profile",
            onClick = onProfileClick
        ),
        ProfileOption(
            icon = AppIcons.NotificationIcon,
            title = "Notification",
            onClick = onNotificationClick
        ),
        ProfileOption(
            icon = AppIcons.LanguageIcon,
            title = "Language",
            onClick = onLanguageClick
        ),
        ProfileOption(
            icon = AppIcons.ModeIcon,
            title = "Night Mode",
        ),
        ProfileOption(
            icon = AppIcons.LocationOutlinedIcon,
            title = "Localisation",
            onClick = onLocalisationClick
        ),
        ProfileOption(
            icon = AppIcons.DeleteOutlinedIcon,
            title = "Log out",
            onClick = onLogOutClick
        )
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    AppText(
                        text = "Setting",
                        textAlignment = TextAlign.Center,
                        color = AppColor.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        AppIconArrowBack()
                    }
                },
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
            ProfileOptionsCard(items)
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
private fun ProfileOptionsCard(options: List<ProfileOption>) {
    var isEnabled by remember {
        mutableStateOf(false)
    }
    Card(
        shape = MediumRoundedCornerShape,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            options.forEachIndexed { index, option ->
                ProfileRow(
                    option = option,
                    isSwitch = option.title == "Night Mode",
                    isChecked = isEnabled,
                    onCheckedChange = {
                        isEnabled = it
                        AppCompatDelegate.setDefaultNightMode(
                            if (isEnabled)
                                AppCompatDelegate.MODE_NIGHT_YES
                            else {
                                AppCompatDelegate.MODE_NIGHT_NO
                            }
                        )
                        Log.d("NavHostScreen", "getDefaultNightMode: ${AppCompatDelegate.getDefaultNightMode()}")
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
            .clickable { if (!isSwitch) option.onClick() }
            .padding(
                horizontal = Baseline5,
                vertical = Baseline4_5
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AppIcon(
            painter = option.icon,
            tint = AppColor.GreenRacing
        )
        Spacer(
            modifier = Modifier.width(Baseline5)
        )
        AppText(
            text = option.title,
            color = AppColor.Black,
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