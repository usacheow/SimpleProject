package com.usacheow.coreuicompose.tools

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.DimenValues
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

fun Modifier.defaultBorder(shape: Shape) = composed {
    border(
        width = 1.dp,
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

@SuppressLint("ComposableModifierFactory")
@Composable
fun Modifier.defaultTileRipple(
    delay: Duration = 500.milliseconds,
    shape: Shape = MaterialTheme.shapes.small,
    onClick: (() -> Unit)?,
) = clip(shape)
    .doOnClick(delay, onClick)
    .padding(DimenValues.ripple_inner_padding)

fun Modifier.mockClick() = clickable(
    interactionSource = MutableInteractionSource(),
    indication = null,
    onClick = {},
)

fun Modifier.doOnClick(
    delay: Duration = 500.milliseconds,
    onClick: (() -> Unit)?,
): Modifier = composed {
    if (onClick == null) return@composed this

    val enable = remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    clickable(enabled = onClick != null) {
        if (enable.value) {
            onClick?.invoke()
        }
        enable.value = false
        coroutineScope.launch {
            delay(delay)
            enable.value = true
        }
    }
}