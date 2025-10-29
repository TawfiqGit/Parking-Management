package com.tawfiqdev.design_system.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tawfiqdev.design_system.theme.AppColor
import com.tawfiqdev.design_system.theme.ExtraLargeRoundedCornerShape
import com.tawfiqdev.design_system.theme.ExtraSmallRoundedCornerShape
import com.tawfiqdev.design_system.theme.LargeRoundedCornerShape
import com.tawfiqdev.design_system.theme.MediumRoundedCornerShape
import com.tawfiqdev.design_system.theme.SmallRoundedCornerShape

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 16.sp,
    shape: RoundedCornerShape = ExtraSmallRoundedCornerShape,
    backgroundColor: Color = AppColor.GreenRacing,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.4f),
            disabledContentColor = contentColor.copy(alpha = 0.6f),
        ),
        contentPadding = ButtonDefaults.ContentPadding
    ) {
        AppText(
            text = text,
            color = contentColor,
            fontSize = fontSize,
            textAlignment = TextAlign.Center
        )
    }
}

@Composable
fun AppOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 16.sp,
    shape: RoundedCornerShape = ExtraSmallRoundedCornerShape,
    borderColor: Color = AppColor.GreenRacing,
    borderWidth: Dp = 1.dp,
    backgroundColor: Color = AppColor.White,
    contentColor: Color = AppColor.GreenRacing,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        border = BorderStroke(borderWidth, borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.2f),
            disabledContentColor = contentColor.copy(alpha = 0.4f),
        ),
        contentPadding = ButtonDefaults.ContentPadding
    ) {
        AppText(
            text = text,
            color = contentColor,
            fontSize = fontSize,
            textAlignment = TextAlign.Center
        )
    }
}

@Composable
fun ContinueButton(text: String, onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            )
        ) {
            Text(text = text, color = Color.White)
        }
    }
}

@Preview
@Composable
fun ContinueButtonPreview() {
    ContinueButton(text = "Continue", onClick = {})
}

@Preview
@Composable
fun AppOutlineButtonPreview() {
    AppOutlinedButton(text = "Hello World", shape = ExtraSmallRoundedCornerShape) {}
}

@Preview
@Composable
fun AppButton() {
    AppButton(text = "Hello World") {}
}

@Preview
@Composable
fun ExtraSmallRoundedCornerShapedButton() {
    AppButton(text = "Hello World", shape = ExtraSmallRoundedCornerShape) {}
}

@Preview
@Composable
fun SmallRoundedCornerShapeButton() {
    AppButton(text = "Hello World", shape = SmallRoundedCornerShape) {}
}

@Preview
@Composable
fun MediumRoundedCornerShapeButton() {
    AppButton(text = "Hello World", shape = MediumRoundedCornerShape) {}
}

@Preview
@Composable
fun LargeRoundedCornerShapeButton() {
    AppButton(text = "Hello World", shape = LargeRoundedCornerShape) {}
}

@Preview(device = "spec:width=411dp,height=891dp")
@Composable
fun ExtraLargeRoundedCornerShapeButton() {
    AppButton(text = "Hello World", shape = ExtraLargeRoundedCornerShape) {}
}