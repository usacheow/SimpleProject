package com.usacheow.coreuitheme.compose

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalSpecificColorScheme = staticCompositionLocalOf { LightSpecificColorScheme }
internal val DarkColorScheme by lazy { DarkSpecificColorScheme.toColorScheme() }
internal val LightColorScheme by lazy { LightSpecificColorScheme.toColorScheme() }

internal val LightSpecificColorScheme = SpecificColorScheme(
    primary = Color(0xFF507844),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF9ABA90),
    onPrimaryContainer = Color(0xFF253022),
    primaryInverse = Color(0xFF8FD17D),

    secondary = Color(0xFFAE4F2F),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFC3B6B2),
    onSecondaryContainer = Color(0xFF2B1D19),

    tertiary = Color(0xFFDBD655),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFF4F3D1),
    onTertiaryContainer = Color(0xFF312911),

    error = Color(0xFFB3261E),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFF9DEDC),
    onErrorContainer = Color(0xFF410E0B),

    outline = Color(0xFFCECCCC),
    outlineVariant = Color(0xFFCECCCC),
    shimmer = Color(0xFFF4F4F4),
    scrim = Color(0xB3FFFFFF),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFEEEEEE),
    onSurfaceVariant = Color(0xFF454F4B),
    surfaceInverse = Color(0xFF303331),
    onSurfaceInverse = Color(0xFFEFF4F1),

    symbolPrimary = Color(0xFF1C1B1F),
    symbolPrimaryInverse = Color(0xFFFFFFFF),
    symbolSecondary = Color(0x9A1C1B1F),
    symbolSecondaryInverse = Color(0x9AFFFFFF),
    symbolTertiary = Color(0x651C1B1F),
    symbolTertiaryInverse = Color(0x65FFFFFF),
)

internal val DarkSpecificColorScheme = SpecificColorScheme(
    primary = Color(0xFF8FD17D),
    onPrimary = Color(0xFF3B721E),
    primaryContainer = Color(0xFF253022),
    onPrimaryContainer = Color(0xFF9ABA90),
    primaryInverse = Color(0xFF507844),

    secondary = Color(0xFF9C4D32),
    onSecondary = Color(0xFF572818),
    secondaryContainer = Color(0xFF4C332C),
    onSecondaryContainer = Color(0xFFC3B6B2),

    tertiary = Color(0xFF656339),
    onTertiary = Color(0xFFD1CE77),
    tertiaryContainer = Color(0xFF4C401B),
    onTertiaryContainer = Color(0xFFF4F3D1),

    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410),
    errorContainer = Color(0xFF8C1D18),
    onErrorContainer = Color(0xFFF2B8B5),

    outline = Color(0xFF9E9E9E),
    outlineVariant = Color(0xFF9E9E9E),
    shimmer = Color(0xFF55565A),
    scrim = Color(0xB3000000),

    background = Color(0xFF000000),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF000000),
    onSurface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFF454F49),
    onSurfaceVariant = Color(0xFFD2D3D2),
    surfaceInverse = Color(0xFFEAEAEA),
    onSurfaceInverse = Color(0xFF303331),

    symbolPrimary = Color(0xFFFFFFFF),
    symbolPrimaryInverse = Color(0xFF1C1B1F),
    symbolSecondary = Color(0x9AFFFFFF),
    symbolSecondaryInverse = Color(0x9A1C1B1F),
    symbolTertiary = Color(0x65FFFFFF),
    symbolTertiaryInverse = Color(0x651C1B1F),
)

data class SpecificColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val primaryInverse: Color,

    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,

    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,

    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,

    val outline: Color,
    val outlineVariant: Color,
    val shimmer: Color,
    val scrim: Color,

    val background: Color,
    val onBackground: Color,

    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val surfaceInverse: Color,
    val onSurfaceInverse: Color,
    val surfaceTint: Color = primary,

    val symbolPrimary: Color,
    val symbolPrimaryInverse: Color,
    val symbolSecondary: Color,
    val symbolSecondaryInverse: Color,
    val symbolTertiary: Color,
    val symbolTertiaryInverse: Color,

    val white_10: Color = Color(0x33FFFFFF),
    val white_20: Color = Color(0x33FFFFFF),
    val white_30: Color = Color(0x33FFFFFF),
    val white_40: Color = Color(0x33FFFFFF),
    val white_50: Color = Color(0x80FFFFFF),
    val white_60: Color = Color(0x99ffffff),
    val white_70: Color = Color(0x99ffffff),
    val white_80: Color = Color(0xCCFFFFFF),
    val white_90: Color = Color(0xE6FFFFFF),
    val white: Color = Color(0xFFFFFFFF),

    val black_10: Color = Color(0x1A000000),
    val black_20: Color = Color(0x1A000000),
    val black_30: Color = Color(0x4D000000),
    val black_40: Color = Color(0x4D000000),
    val black_50: Color = Color(0x80000000),
    val black_60: Color = Color(0x80000000),
    val black_70: Color = Color(0x80000000),
    val black_80: Color = Color(0xCC000000),
    val black_90: Color = Color(0xCC000000),
    val black: Color = Color(0xFF000000),

    val transparent: Color = Color(0x00000000),
)