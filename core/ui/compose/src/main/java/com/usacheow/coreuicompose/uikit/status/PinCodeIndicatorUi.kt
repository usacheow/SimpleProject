package com.usacheow.coreuicompose.uikit.status

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.usacheow.coreuitheme.compose.AppTheme

enum class PinCodeStatus {

    Normal, Checking, Loading, Success, Error,
}

data class PinCodeIndicatorColor(
    val filled: Color,
    val unfilled: Color,
)

object PinCodeIndicatorConfig {

    val IndicatorDotSizeMin = 12.dp
    val IndicatorDotSizeMax = 14.dp
    val MarginHorizontal = 20.dp

    @Composable
    fun defaultColor() = PinCodeIndicatorColor(
        filled = AppTheme.specificColorScheme.tertiary,
        unfilled = AppTheme.specificColorScheme.surfaceVariant,
    )

    @Composable
    fun successColor() = PinCodeIndicatorColor(
        filled = AppTheme.specificColorScheme.primary,
        unfilled = AppTheme.specificColorScheme.surfaceVariant,
    )

    @Composable
    fun errorColor() = PinCodeIndicatorColor(
        filled = AppTheme.specificColorScheme.error,
        unfilled = AppTheme.specificColorScheme.surfaceVariant,
    )
}

@Composable
fun PinCodeIndicatorUi(
    modifier: Modifier = Modifier,
    inputtedCodeLength: Int,
    codeLength: Int,
    status: PinCodeStatus = PinCodeStatus.Normal,
) {
    val color = when (status) {
        PinCodeStatus.Normal, PinCodeStatus.Checking -> PinCodeIndicatorConfig.defaultColor()
        PinCodeStatus.Loading, PinCodeStatus.Success -> PinCodeIndicatorConfig.successColor()
        PinCodeStatus.Error -> PinCodeIndicatorConfig.errorColor()
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(PinCodeIndicatorConfig.MarginHorizontal),
    ) {
        repeat(codeLength) { index ->
            Dot(
                color = if (index < inputtedCodeLength) color.filled else color.unfilled,
                index = index,
                status = status,
            )
        }
    }
}

@Composable
private fun Dot(
    color: Color,
    index: Int,
    status: PinCodeStatus,
) {
    val dotSize = if (status == PinCodeStatus.Loading) {
        val circleSize = rememberInfiniteTransition().animateFloat(
            initialValue = PinCodeIndicatorConfig.IndicatorDotSizeMin.value,
            targetValue = PinCodeIndicatorConfig.IndicatorDotSizeMax.value,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, delayMillis = 100, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Reverse,
                initialStartOffset = StartOffset(40 * index),
            ),
        )
        circleSize.value.dp
    } else {
        PinCodeIndicatorConfig.IndicatorDotSizeMin
    }

    val dotColor by animateColorAsState(targetValue = color)

    Box(
        modifier = Modifier.size(PinCodeIndicatorConfig.IndicatorDotSizeMax),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(dotSize)
                .clip(CircleShape)
                .background(dotColor),
        )
    }
}