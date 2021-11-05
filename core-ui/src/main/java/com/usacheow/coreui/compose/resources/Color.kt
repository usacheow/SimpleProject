package com.usacheow.coreui.compose.resources

import androidx.compose.ui.graphics.Color

val LightCommonColors = Colors(

    primary = Color(0xFF6750A4),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFEADDFF),
    onPrimaryContainer = Color(0xFF21005D),
    primaryInverse = Color(0xFFD0BCFF),

    secondary = Color(0xFF625B71),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFE8DEF8),
    onSecondaryContainer = Color(0xFF1D192B),

    tertiary = Color(0xFF7D5260),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFFD8E4),
    onTertiaryContainer = Color(0xFF31111D),

    error = Color(0xFFB3261E),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFF9DEDC),
    onErrorContainer = Color(0xFF410E0B),

    outline = Color(0xFF79747E),

    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),

    surface = Color(0xFFFFFBFE),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = Color(0xFF49454F),
    surfaceInverse = Color(0xFF313033),
    onSurfaceInverse = Color(0xFFF4EFF4),

    colorStatusBar = Color(0x4D000000),
    colorNavigationBar = Color(0x4D000000),
    colorStatusBarForModal = Color(0x4D000000),
    colorNavigationBarForModal = Color(0x4D000000),

    shimmer = Color(0xFFDBDEE6),
)

val DarkCommonColors = Colors(

    primary = Color(0xFFD0BCFF),
    onPrimary = Color(0xFF381E72),
    primaryContainer = Color(0xFF4F378B),
    onPrimaryContainer = Color(0xFFEADDFF),
    primaryInverse = Color(0xFF6750A4),

    secondary = Color(0xFFCCC2DC),
    onSecondary = Color(0xFF332D41),
    secondaryContainer = Color(0xFF4A4458),
    onSecondaryContainer = Color(0xFFE8DEF8),

    tertiary = Color(0xFFEFB8C8),
    onTertiary = Color(0xFF492532),
    tertiaryContainer = Color(0xFF633B48),
    onTertiaryContainer = Color(0xFFFFD8E4),

    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410),
    errorContainer = Color(0xFF8C1D18),
    onErrorContainer = Color(0xFFF2B8B5),

    outline = Color(0xFF938F99),

    background = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE6E1E5),

    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = Color(0xFF49454F),
    onSurfaceVariant = Color(0xFFCAC4D0),
    surfaceInverse = Color(0xFFE6E1E5),
    onSurfaceInverse = Color(0xFF313033),

    colorStatusBar = Color(0x00000000),
    colorNavigationBar = Color(0x03000000),
    colorStatusBarForModal = Color(0x00000000),
    colorNavigationBarForModal = Color(0x03000000),

    shimmer = Color(0xFF55565A),
)

data class Colors(

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

    val background: Color,
    val onBackground: Color,

    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val surfaceInverse: Color,
    val onSurfaceInverse: Color,

    val colorStatusBar: Color,
    val colorNavigationBar: Color,
    val colorStatusBarForModal: Color,
    val colorNavigationBarForModal: Color,

    val shimmer: Color,

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