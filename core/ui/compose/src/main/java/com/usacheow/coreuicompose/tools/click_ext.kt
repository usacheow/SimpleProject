package com.usacheow.coreuicompose.tools

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.usacheow.coreuitheme.compose.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

fun Modifier.mockClick() = clickable(
    interactionSource = MutableInteractionSource(),
    indication = null,
    onClick = {},
)

@SuppressLint("ComposableModifierFactory")
@Composable
fun Modifier.defaultTileRipple(
    delay: Duration = 500.milliseconds,
    shape: Shape = MaterialTheme.shapes.medium,
    padding: Dp = AppTheme.specificValues.ripple_inner_padding,
    onClick: (() -> Unit)?,
) = clip(shape)
    .doOnClick(delay, onClick)
    .padding(padding)

fun Modifier.doOnClick(
    delay: Duration = 500.milliseconds,
    onClick: (() -> Unit)?,
): Modifier = composed {
    if (onClick == null) return@composed this

    val enable = remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    clickable(enabled = true) {
        if (enable.value) {
            onClick()
        }
        enable.value = false
        coroutineScope.launch {
            delay(delay)
            enable.value = true
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.doOnClick(
    delay: Duration = 500.milliseconds,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
): Modifier = composed {
    val enable = remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    combinedClickable(
        enabled = enable.value,
        onClick = {
            if (enable.value) {
                onClick()
            }
            enable.value = false
            coroutineScope.launch {
                delay(delay)
                enable.value = true
            }
        },
        onLongClick = onLongClick,
    )
}