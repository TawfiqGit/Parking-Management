package com.tawfiqdev.design_system.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tawfiqdev.design_system.icone.AppIcons
import com.tawfiqdev.design_system.theme.AppColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AnimatedSplashScreen(
    isReadyFlow: StateFlow<Boolean>,
    onFinished: () -> Unit
) {
    val isReady by isReadyFlow.collectAsState()

    var alpha by remember { mutableFloatStateOf(0f) }
    var scale by remember { mutableFloatStateOf(0.85f) }
    val rotation by animateFloatAsState(
        targetValue = if (isReady) 0f else 720f,
        animationSpec = tween(1500, easing = LinearOutSlowInEasing)
    )

    // Fade-in +  zoom
    LaunchedEffect(Unit) {
        val duration = 700
        val steps = 14
        val stepDelay = duration / steps

        repeat(steps) {
            alpha += (1f - 0f) / steps
            scale += (1.0f - 0.85f) / steps
            delay(stepDelay.toLong())
        }
        alpha = 1f
        scale = 1f
    }

    LaunchedEffect(isReady) {
        if (isReady) {
            delay(3000)
            onFinished()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.GreenTeal),
        contentAlignment = Alignment.Center
    ) {
        AppIcon(
            modifier = Modifier.graphicsLayer{
                rotationZ = rotation
            },
            painter = AppIcons.AppIcon,
            size = 200.dp,
            tint = AppColor.RoseSeaShell
        )
    }
}