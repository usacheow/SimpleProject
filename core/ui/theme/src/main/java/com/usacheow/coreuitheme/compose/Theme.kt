package com.usacheow.coreuitheme.compose

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val LocalCommonColors = staticCompositionLocalOf { LightCommonColors }

private val DarkColorPalette = DarkCommonColors.toColorScheme()
private val LightColorPalette = LightCommonColors.toColorScheme()

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val commonColors = when {
        darkTheme -> DarkCommonColors
        else -> LightCommonColors
    }
    val colorScheme = when {
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    CompositionLocalProvider(
        LocalCommonColors provides commonColors,
        LocalIndication provides rememberRipple(),
        LocalRippleTheme provides AppRippleTheme,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            shapes = AppShapes,
            typography = AppTypography,
            content = { Material2ThemeSupport(darkTheme, content) },
        )
    }
}

@Immutable
private object AppRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor() = AppTheme.commonColors.symbolPrimary

    @Composable
    override fun rippleAlpha() = RippleAlpha(
        draggedAlpha = .16f,
        focusedAlpha = .12f,
        hoveredAlpha = .08f,
        pressedAlpha = .12f,
    )
}

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

object AppTheme {

    val commonColors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalCommonColors.current

    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes
}