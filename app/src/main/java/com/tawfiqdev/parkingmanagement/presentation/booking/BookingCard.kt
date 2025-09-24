package com.tawfiqdev.parkingmanagement.presentation.booking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tawfiqdev.design_system.components.AppButton
import com.tawfiqdev.design_system.components.AppOutlinedButton
import com.tawfiqdev.design_system.components.AppText
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.design_system.theme.MediumRoundedCornerShape
import com.tawfiqdev.design_system.theme.SmallLargeRoundedCornerShape
import com.tawfiqdev.design_system.theme.SmallMediumRoundedCornerShape
import com.tawfiqdev.model.Booking

@Composable
fun BookingCard(
    booking: Booking,
    onRebook: () -> Unit,
    onETicket: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        shape = SmallLargeRoundedCornerShape,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp),
        modifier = modifier
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.height(IntrinsicSize.Min)
            ) {
                AsyncImage(
                    model = booking.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(96.dp)
                        .clip(MediumRoundedCornerShape)
                )

                Spacer(Modifier.width(12.dp))

                Column(Modifier.weight(1f)) {
                    CategoryChip(category = booking.category)

                    Spacer(Modifier.height(2.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = booking.title,
                            color = AppColor.GreenRacing,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            maxLines = 1,
                        )
                        Spacer(Modifier.width(6.dp))
                        RatingPill(rating = booking.rating)
                    }

                    Spacer(Modifier.height(2.dp))

                    LocationRow(city = booking.city)

                    Spacer(Modifier.height(6.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AppText(
                            text = booking.pricePerHour,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AppColor.GreenRacing
                        )
                        AppText(modifier = Modifier.padding(top = 3.dp), text = " /hr", color = AppColor.GreenRacing)
                    }
                }
            }

            Spacer(Modifier.height(14.dp))

            Row {
                AppOutlinedButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    text = "Re-Book",
                    shape = RoundedCornerShape(28.dp),
                    onClick = onRebook
                )

                Spacer(Modifier.width(12.dp))

                AppButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    text = "E-Ticket",
                    shape = RoundedCornerShape(28.dp),
                    onClick = onETicket
                )
            }
        }
    }
}

@Composable
fun RatingPill(rating: Double) {
    AssistChip(
        onClick = {},
        label = {
            AppText(
                text = String.format("%.1f", rating),
                color = AppColor.Black
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = AppColor.RoseSpanish
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = AppColor.RoseSeaShell.copy(alpha = 0.35f)
        ),
        border = AssistChipDefaults.assistChipBorder(enabled = true , borderColor = Color.Transparent),
        shape = SmallMediumRoundedCornerShape
    )
}

@Composable
fun CategoryChip(category : String) {
    AssistChip(
        onClick = {},
        label = {
            AppText(
                text = category,
                color = AppColor.GreenRacing,
                fontSize = 12.sp
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = AppColor.GreenRacing.copy(alpha = 0.12f),
            labelColor = AppColor.GreenRacing
        ),
        border = AssistChipDefaults.assistChipBorder(enabled = true , borderColor = Color.Transparent)
    )
}

@Composable
fun LocationRow(city: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = null,
            tint = AppColor.GreyDark.copy(alpha = 0.6f),
            modifier = Modifier.size(18.dp)
        )
        Spacer(Modifier.width(6.dp))
        AppText(
            text = city,
            color = AppColor.GreyDark.copy(alpha = 0.7f)
        )
    }
}
