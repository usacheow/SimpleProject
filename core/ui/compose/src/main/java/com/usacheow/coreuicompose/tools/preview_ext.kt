package com.usacheow.coreuicompose.tools

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.usacheow.coreuitheme.compose.AppTheme

@Composable
fun SimplePreview(content: @Composable () -> Unit) {
    AppTheme {
        Surface(
            color = AppTheme.colorScheme.surface,
            contentColor = AppTheme.colorScheme.onSurface,
            content = content,
        )
    }
}