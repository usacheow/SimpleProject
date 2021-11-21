package com.usacheow.coreui.compose.resources

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

private val DarkColorPalette = darkColorScheme(
    primary = DarkCommonColors.primary,
    onPrimary = DarkCommonColors.onPrimary,
    primaryContainer = DarkCommonColors.primaryContainer,
    onPrimaryContainer = DarkCommonColors.onPrimaryContainer,
    inversePrimary = DarkCommonColors.primaryInverse,
    secondary = DarkCommonColors.secondary,
    onSecondary = DarkCommonColors.onSecondary,
    secondaryContainer = DarkCommonColors.secondaryContainer,
    onSecondaryContainer = DarkCommonColors.onSecondaryContainer,
    tertiary = DarkCommonColors.tertiary,
    onTertiary = DarkCommonColors.onTertiary,
    tertiaryContainer = DarkCommonColors.tertiaryContainer,
    onTertiaryContainer = DarkCommonColors.onTertiaryContainer,
    background = DarkCommonColors.background,
    onBackground = DarkCommonColors.onBackground,
    surface = DarkCommonColors.surface,
    onSurface = DarkCommonColors.onSurface,
    surfaceVariant = DarkCommonColors.surfaceVariant,
    onSurfaceVariant = DarkCommonColors.onSurfaceVariant,
    inverseSurface = DarkCommonColors.surfaceInverse,
    inverseOnSurface = DarkCommonColors.onSurfaceInverse,
    error = DarkCommonColors.error,
    onError = DarkCommonColors.onError,
    errorContainer = DarkCommonColors.errorContainer,
    onErrorContainer = DarkCommonColors.onErrorContainer,
    outline = DarkCommonColors.outline,
)
private val LightColorPalette = lightColorScheme(
    primary = LightCommonColors.primary,
    onPrimary = LightCommonColors.onPrimary,
    primaryContainer = LightCommonColors.primaryContainer,
    onPrimaryContainer = LightCommonColors.onPrimaryContainer,
    inversePrimary = LightCommonColors.primaryInverse,
    secondary = LightCommonColors.secondary,
    onSecondary = LightCommonColors.onSecondary,
    secondaryContainer = LightCommonColors.secondaryContainer,
    onSecondaryContainer = LightCommonColors.onSecondaryContainer,
    tertiary = LightCommonColors.tertiary,
    onTertiary = LightCommonColors.onTertiary,
    tertiaryContainer = LightCommonColors.tertiaryContainer,
    onTertiaryContainer = LightCommonColors.onTertiaryContainer,
    background = LightCommonColors.background,
    onBackground = LightCommonColors.onBackground,
    surface = LightCommonColors.surface,
    onSurface = LightCommonColors.onSurface,
    surfaceVariant = LightCommonColors.surfaceVariant,
    onSurfaceVariant = LightCommonColors.onSurfaceVariant,
    inverseSurface = LightCommonColors.surfaceInverse,
    inverseOnSurface = LightCommonColors.onSurfaceInverse,
    error = LightCommonColors.error,
    onError = LightCommonColors.onError,
    errorContainer = LightCommonColors.errorContainer,
    onErrorContainer = LightCommonColors.onErrorContainer,
    outline = LightCommonColors.outline,
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val commonColors = when {
        darkTheme -> DarkCommonColors
        else -> LightCommonColors
    }
    val mainPaletteColors = when {
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    CompositionLocalProvider(LocalCommonColors provides commonColors) {
        MaterialTheme(
            colorScheme = mainPaletteColors,
            typography = Typography,
            content = content,
        )
    }
}

val LocalCommonColors = staticCompositionLocalOf { LightCommonColors }