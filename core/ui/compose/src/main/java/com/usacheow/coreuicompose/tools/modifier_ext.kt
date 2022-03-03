package com.usacheow.coreuicompose.tools

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp
import com.usacheow.coreuitheme.compose.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

fun Modifier.defaultBorder() = composed {
    border(
        width = 1.dp,
        color = AppTheme.commonColors.outline,
        shape = MaterialTheme.shapes.medium,
    )
}

fun Modifier.doOnClick(
    delay: Duration = 500.milliseconds,
    onClick: (() -> Unit)?,
): Modifier = composed {
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