package com.usacheow.coreui.compose.tools

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.resources.AppTheme

@Composable
fun SimpleWidgetStatePreview(content: @Composable () -> Unit) {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            content = content,
        )
    }
}

@Composable
fun LazySimpleWidgetStatePreview(itemsInitializer: @Composable () -> List<WidgetState>) {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) {
            val items = itemsInitializer()
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items.forEach { item { it.Draw() } }
            }
        }
    }
}