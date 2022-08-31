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
    outlineVariant = outlineVariant,

    scrim = scrim,
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
    outline = if (isLight) DefaultLightSpecificColorScheme.outline
    else DefaultDarkSpecificColorScheme.outline,
    outlineVariant = outlineVariant,
    scrim = scrim,
    shimmer = if (isLight) DefaultLightSpecificColorScheme.shimmer
    else DefaultDarkSpecificColorScheme.shimmer,
    background = background,
    onBackground = onBackground,
    surface = surface,
    onSurface = onSurface,
    surfaceVariant = surfaceVariant,
    onSurfaceVariant = onSurfaceVariant,
    surfaceInverse = inverseSurface,
    onSurfaceInverse = inverseOnSurface,
    symbolPrimary = if (isLight) DefaultLightSpecificColorScheme.symbolPrimary
    else DefaultDarkSpecificColorScheme.symbolPrimary,
    symbolPrimaryInverse = if (isLight) DefaultLightSpecificColorScheme.symbolPrimaryInverse
    else DefaultDarkSpecificColorScheme.symbolPrimaryInverse,
    symbolSecondary = if (isLight) DefaultLightSpecificColorScheme.symbolSecondary
    else DefaultDarkSpecificColorScheme.symbolSecondary,
    symbolSecondaryInverse = if (isLight) DefaultLightSpecificColorScheme.symbolSecondaryInverse
    else DefaultDarkSpecificColorScheme.symbolSecondaryInverse,
    symbolTertiary = if (isLight) DefaultLightSpecificColorScheme.symbolTertiary
    else DefaultDarkSpecificColorScheme.symbolTertiary,
    symbolTertiaryInverse = if (isLight) DefaultLightSpecificColorScheme.symbolTertiaryInverse
    else DefaultDarkSpecificColorScheme.symbolTertiaryInverse,
)