package com.usacheow.featurewidget.glance

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.glance.unit.ColorProvider
import com.usacheow.featurewidget.R

internal val LocalGlanceColorScheme = staticCompositionLocalOf { DefaultGlanceColorScheme }

internal val DefaultGlanceColorScheme = GlanceColorScheme(
    primary = ColorProvider(R.color.colorPrimary),
    onPrimary = ColorProvider(R.color.colorOnPrimary),
    primaryContainer = ColorProvider(R.color.colorPrimaryContainer),
    onPrimaryContainer = ColorProvider(R.color.colorOnPrimaryContainer),
    secondary = ColorProvider(R.color.colorSecondary),
    onSecondary = ColorProvider(R.color.colorOnSecondary),
    secondaryContainer = ColorProvider(R.color.colorSecondaryContainer),
    onSecondaryContainer = ColorProvider(R.color.colorOnSecondaryContainer),
    tertiary = ColorProvider(R.color.colorTertiary),
    onTertiary = ColorProvider(R.color.colorOnTertiary),
    tertiaryContainer = ColorProvider(R.color.colorTertiaryContainer),
    onTertiaryContainer = ColorProvider(R.color.colorOnTertiaryContainer),
    error = ColorProvider(R.color.colorError),
    errorContainer = ColorProvider(R.color.colorErrorContainer),
    onError = ColorProvider(R.color.colorOnError),
    onErrorContainer = ColorProvider(R.color.colorOnErrorContainer),
    background = ColorProvider(R.color.colorBackground),
    onBackground = ColorProvider(R.color.colorOnBackground),
    surface = ColorProvider(R.color.colorSurface),
    onSurface = ColorProvider(R.color.colorOnSurface),
    surfaceVariant = ColorProvider(R.color.colorSurfaceVariant),
    onSurfaceVariant = ColorProvider(R.color.colorOnSurfaceVariant),
    outline = ColorProvider(R.color.colorOutline),
    inverseOnSurface = ColorProvider(R.color.colorOnSurfaceInverse),
    inverseSurface = ColorProvider(R.color.colorSurfaceInverse),
    inversePrimary = ColorProvider(R.color.colorPrimaryInverse),
)

data class GlanceColorScheme(
    val primary: ColorProvider,
    val onPrimary: ColorProvider,
    val primaryContainer: ColorProvider,
    val onPrimaryContainer: ColorProvider,
    val secondary: ColorProvider,
    val onSecondary: ColorProvider,
    val secondaryContainer: ColorProvider,
    val onSecondaryContainer: ColorProvider,
    val tertiary: ColorProvider,
    val onTertiary: ColorProvider,
    val tertiaryContainer: ColorProvider,
    val onTertiaryContainer: ColorProvider,
    val error: ColorProvider,
    val errorContainer: ColorProvider,
    val onError: ColorProvider,
    val onErrorContainer: ColorProvider,
    val background: ColorProvider,
    val onBackground: ColorProvider,
    val surface: ColorProvider,
    val onSurface: ColorProvider,
    val surfaceVariant: ColorProvider,
    val onSurfaceVariant: ColorProvider,
    val outline: ColorProvider,
    val inverseOnSurface: ColorProvider,
    val inverseSurface: ColorProvider,
    val inversePrimary: ColorProvider,
)