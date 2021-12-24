package com.usacheow.coreui.compose.resources

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Shapes
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalCommonColors = staticCompositionLocalOf { LightCommonColors }
val LocalShapes = staticCompositionLocalOf { Shapes }

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
        LocalShapes provides Shapes,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content,
        )
    }
}

object AppTheme {

    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val commonColors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalCommonColors.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
}