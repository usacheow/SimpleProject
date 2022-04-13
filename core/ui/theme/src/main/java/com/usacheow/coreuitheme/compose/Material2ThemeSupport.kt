package com.usacheow.coreuitheme.compose

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable

@Composable
internal fun Material2ThemeSupport(darkTheme: Boolean, content: @Composable () -> Unit) {
    MaterialTheme(
        colors = AppTheme.colorScheme.toMaterial2(isLight = !darkTheme),
        shapes = AppTheme.shapes.toMaterial2(),
        typography = AppTheme.typography.toMaterial2(),
        content = content,
    )
}

private fun ColorScheme.toMaterial2(isLight: Boolean) = Colors(
    primary = primary,
    primaryVariant = primaryContainer,
    secondary = secondary,
    secondaryVariant = secondaryContainer,
    background = background,
    surface = surface,
    error = error,
    onPrimary = onPrimary,
    onSecondary = onSecondary,
    onBackground = onBackground,
    onSurface = onSurface,
    onError = onError,
    isLight = isLight,
)

private fun Shapes.toMaterial2() = androidx.compose.material.Shapes(
    small = small,
    medium = medium,
    large = large,
)

private fun Typography.toMaterial2() = androidx.compose.material.Typography(
    h1 = displayLarge,
    h2 = displayMedium,
    h3 = displaySmall,
    h4 = headlineLarge,
    h5 = headlineMedium,
    h6 = headlineSmall,
    subtitle1 = titleLarge,
    subtitle2 = titleMedium,
    body1 = bodyLarge,
    body2 = bodyMedium,
    button = labelLarge,
    caption = labelMedium,
    overline = labelSmall,
)