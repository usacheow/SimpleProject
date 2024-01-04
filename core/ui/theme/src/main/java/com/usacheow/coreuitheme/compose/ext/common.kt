package com.usacheow.coreuitheme.compose

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography

fun SpecificColorScheme.toColorScheme() = ColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    primaryContainer = primary,
    onPrimaryContainer = onPrimary,
    inversePrimary = primaryInverse,

    secondary = secondary,
    onSecondary = onSecondary,
    secondaryContainer = secondary,
    onSecondaryContainer = onSecondary,

    tertiary = tertiary,
    onTertiary = onTertiary,
    tertiaryContainer = tertiary,
    onTertiaryContainer = onTertiary,

    background = background1,
    onBackground = onBackground1,
    surface = surface1,
    onSurface = onSurface1,
    surfaceVariant = surface2,
    onSurfaceVariant = onSurface2,
    inverseSurface = surfaceInverse,
    inverseOnSurface = onSurfaceInverse,
    surfaceTint = primary,

    error = error,
    onError = onError,
    errorContainer = error,
    onErrorContainer = onError,

    outline = border,
    outlineVariant = border,

    scrim = scrim,
)

fun SpecificTypography.toTypography() = Typography(
    displayLarge = displayLarge,
    displayMedium = displayLarge,
    displaySmall = displayLarge,
    headlineLarge = headlineLarge,
    headlineMedium = headlineMedium,
    headlineSmall = headlineSmall,
    titleLarge = titleLarge,
    titleMedium = titleMedium,
    titleSmall = titleSmall,
    bodyLarge = bodyLarge,
    bodyMedium = bodyMedium,
    bodySmall = bodyMedium,
    labelLarge = labelLarge,
    labelMedium = labelLarge,
    labelSmall = labelLarge,
)