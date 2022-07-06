package com.usacheow.coreuitheme.compose

import androidx.compose.material3.ColorScheme

fun SpecificColorScheme.toColorScheme() = ColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    primaryContainer = primaryContainer,
    onPrimaryContainer = onPrimaryContainer,
    inversePrimary = primaryInverse,

    secondary = secondary,
    onSecondary = onSecondary,
    secondaryContainer = secondaryContainer,
    onSecondaryContainer = onSecondaryContainer,

    tertiary = tertiary,
    onTertiary = onTertiary,
    tertiaryContainer = tertiaryContainer,
    onTertiaryContainer = onTertiaryContainer,

    background = background,
    onBackground = onBackground,
    surface = surface,
    onSurface = onSurface,
    surfaceVariant = surfaceVariant,
    onSurfaceVariant = onSurfaceVariant,
    inverseSurface = surfaceInverse,
    inverseOnSurface = onSurfaceInverse,
    surfaceTint = surfaceTint,

    error = error,
    onError = onError,
    errorContainer = errorContainer,
    onErrorContainer = onErrorContainer,

    outline = outline,
)

fun ColorScheme.toSpecificColorScheme(isLight: Boolean) = SpecificColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    primaryContainer = primaryContainer,
    onPrimaryContainer = onPrimaryContainer,
    primaryInverse = inversePrimary,
    secondary = secondary,
    onSecondary = onSecondary,
    secondaryContainer = secondaryContainer,
    onSecondaryContainer = onSecondaryContainer,
    tertiary = tertiary,
    onTertiary = onTertiary,
    tertiaryContainer = tertiaryContainer,
    onTertiaryContainer = onTertiaryContainer,
    error = error,
    onError = onError,
    errorContainer = errorContainer,
    onErrorContainer = onErrorContainer,
    outline = if (isLight) LightSpecificColorScheme.outline
    else DarkSpecificColorScheme.outline,
    shimmer = if (isLight) LightSpecificColorScheme.shimmer
    else DarkSpecificColorScheme.shimmer,
    background = background,
    onBackground = onBackground,
    surfaceVariant = surfaceVariant,
    onSurfaceVariant = onSurfaceVariant,
    surfaceInverse = inverseSurface,
    onSurfaceInverse = inverseOnSurface,
    symbolPrimary = if (isLight) LightSpecificColorScheme.symbolPrimary
    else DarkSpecificColorScheme.symbolPrimary,
    symbolPrimaryInverse = if (isLight) LightSpecificColorScheme.symbolPrimaryInverse
    else DarkSpecificColorScheme.symbolPrimaryInverse,
    symbolSecondary = if (isLight) LightSpecificColorScheme.symbolSecondary
    else DarkSpecificColorScheme.symbolSecondary,
    symbolSecondaryInverse = if (isLight) LightSpecificColorScheme.symbolSecondaryInverse
    else DarkSpecificColorScheme.symbolSecondaryInverse,
    symbolTertiary = if (isLight) LightSpecificColorScheme.symbolTertiary
    else DarkSpecificColorScheme.symbolTertiary,
    symbolTertiaryInverse = if (isLight) LightSpecificColorScheme.symbolTertiaryInverse
    else DarkSpecificColorScheme.symbolTertiaryInverse,
)