package com.usacheow.coreuicompose.uikit.barcopy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalBottomNavigationHeight = staticCompositionLocalOf { 0.dp }

@Composable
fun SimpleBottomNavigation(
    backgroundColor: Color = MaterialTheme.colorScheme.surface.copy(alpha = .9f),
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    elevation: Dp = BottomNavigationDefaults.Elevation,
    contentPadding: PaddingValues = PaddingValues(),
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        shadowElevation = elevation,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(BottomNavigationDefaults.Height + contentPadding.calculateTopPadding() + contentPadding.calculateBottomPadding())
                .padding(contentPadding)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content
        )
    }
}

object BottomNavigationDefaults {
    val Elevation = 0.dp
    val Height = 56.dp
}