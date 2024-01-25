package com.usacheow.coreuicompose.tools

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

@Composable
fun SystemBarsIconsColor(
    needWhiteAllIcons: Boolean = isSystemInDarkTheme(),
    needWhiteStatusIcons: Boolean = needWhiteAllIcons,
    needWhiteNavigationIcons: Boolean = needWhiteAllIcons,
) {
    val view = LocalView.current
    SideEffect {
        ViewCompat.getWindowInsetsController(view)?.apply {
            isAppearanceLightStatusBars = !needWhiteStatusIcons
            isAppearanceLightNavigationBars = !needWhiteNavigationIcons
        }
    }
}