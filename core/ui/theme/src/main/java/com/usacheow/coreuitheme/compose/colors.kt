package com.usacheow.coreuitheme.compose

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalSpecificColorScheme = staticCompositionLocalOf { LightSpecificColorScheme }
internal val DarkColorScheme by lazy { DarkSpecificColorScheme.toColorScheme() }
internal val LightColorScheme by lazy { LightSpecificColorScheme.toColorScheme() }

internal val LightSpecificColorScheme = SpecificColorScheme(
    primary = Color(0xFF0d6efd),
    onPrimary = Color(0xFFFFFFFF),
    primaryInverse = Color(0xFF0d6efd),

    secondary = Color(0xFFd7eafc),
    onSecondary = Color(0xFF0d6efd),

    tertiary = Color(0xFFFF9800),
    onTertiary = Color(0xFFFFFFFF),

    light1 = Color(0xFFf4f4f4),
    onLight1 = Color(0xFF4f4f4f),

    light2 = Color(0xFFfbfbfb),
    onLight2 = Color(0xFF4f4f4f),

    error = Color(0xFFB3261E),
    onError = Color(0xFFFFFFFF),

    warning = Color(0xFFa26706),
    onWarning = Color(0xFFFFFFFF),

    success = Color(0xFF07ae69),
    onSuccess = Color(0xFFFFFFFF),

    border = Color(0xFFCECCCC),
    shimmer = Color(0xFFF4F4F4),
    scrim = Color(0xB3FFFFFF),
    shadow = Color(0x1ac3c3c3),

    background1 = Color(0xFFFFFFFF),
    background2 = Color(0xFFfbfbfb),
    surface1 = Color(0xFFfbfbfb),
    surface2 = Color(0xFFFFFFFF),
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
    primary = Color(0xFF0d6efd),
    onPrimary = Color(0xFFFFFFFF),
    primaryInverse = Color(0xFF0d6efd),

    secondary = Color(0xFF032a65),
    onSecondary = Color(0xFFFFFFFF),

    tertiary = Color(0xFFFF9800),
    onTertiary = Color(0xFFFFFFFF),

    light1 = Color(0xFF151515),
    onLight1 = Color(0xFFefefef),

    light2 = Color(0xFF232323),
    onLight2 = Color(0xFFfbfbfb),

    error = Color(0xFF601410),
    onError = Color(0xFFFFFFFF),

    warning = Color(0xFFa26706),
    onWarning = Color(0xFFFFFFFF),

    success = Color(0xFF07ae69),
    onSuccess = Color(0xFFFFFFFF),

    border = Color(0xFF9E9E9E),
    shimmer = Color(0xFF55565A),
    scrim = Color(0xB3000000),
    shadow = Color(0x1a000000),

    background1 = Color(0xFF0f0f0f),
    background2 = Color(0xFF0f0f0f),
    surface1 = Color(0xFF0c0c0c),
    surface2 = Color(0xFF000000),
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
    val primaryInverse: Color,

    val secondary: Color,
    val onSecondary: Color,

    val tertiary: Color,
    val onTertiary: Color,

    val light1: Color,
    val onLight1: Color,

    val light2: Color,
    val onLight2: Color,

    val error: Color,
    val onError: Color,

    val warning: Color,
    val onWarning: Color,

    val success: Color,
    val onSuccess: Color,

    val border: Color,
    val shimmer: Color,
    val scrim: Color,
    val shadow: Color,

    val symbolPrimary: Color,
    val symbolPrimaryInverse: Color,
    val symbolSecondary: Color,
    val symbolSecondaryInverse: Color,
    val symbolTertiary: Color,
    val symbolTertiaryInverse: Color,

    val background1: Color,
    val onBackground1: Color = symbolPrimary,
    val background2: Color,
    val onBackground2: Color = symbolPrimary,

    val surface1: Color,
    val onSurface1: Color = symbolPrimary,
    val surface2: Color,
    val onSurface2: Color = symbolPrimary,
    val surfaceInverse: Color,
    val onSurfaceInverse: Color,

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