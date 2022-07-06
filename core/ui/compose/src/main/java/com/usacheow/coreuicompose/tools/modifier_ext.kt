package com.usacheow.coreuicompose.tools

import android.os.Build
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.usacheow.coreuitheme.compose.AppTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

fun Modifier.addIf(
    needAdd: Boolean,
    action: @Composable Modifier.() -> Modifier,
) = composed { if (needAdd) this.action() else this }

fun Modifier.defaultBorder(shape: Shape, width: Dp = .5f.dp) = composed {
    border(
        width = width,
        color = AppTheme.specificColorScheme.outline,
        shape = shape,
    ).clip(shape)
}

fun Modifier.defaultPlaceholder(
    shape: Shape = CircleShape,
) = composed {
    placeholder(
        visible = true,
        shape = shape,
        color = AppTheme.specificColorScheme.shimmer,
        highlight = PlaceholderHighlight.fade(),
    )
}

fun Modifier.gradientBackground(colors: List<Color>, angle: Float) = then(
    Modifier.drawBehind {
        val angleRad = angle / 180f * PI
        val x = cos(angleRad).toFloat()
        val y = sin(angleRad).toFloat()

        val radius = sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
        val offset = center + Offset(x * radius, y * radius)

        val exactOffset = Offset(
            x = min(offset.x.coerceAtLeast(0f), size.width),
            y = size.height - min(offset.y.coerceAtLeast(0f), size.height)
        )

        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(size.width, size.height) - exactOffset,
                end = exactOffset
            ),
            size = size
        )
    }
)

fun Modifier.blur(isEnabled: Boolean) = composed {
    val color = AppTheme.specificColorScheme.white_80
    val radius = animateDpAsState(targetValue = if (isEnabled) 12.dp else 0.dp)
    when {
        isEnabled -> when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> blur(radius = radius.value)
            else -> drawWithContent {
                drawContent()
                drawRect(color = color)
            }
        }
        else -> this
    }
}