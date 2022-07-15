package com.usacheow.coreuitheme.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpSize

val LocalWindowSizeClass = staticCompositionLocalOf<WindowSizeClass> {
    error("CompositionLocal LocalWindowSizeClass not present")
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun PreviewAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    BoxWithConstraints {
        AppTheme(
            windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(maxWidth, maxHeight)),
            darkTheme = darkTheme,
        ) {
            Surface(
                color = AppTheme.specificColorScheme.surface,
                contentColor = AppTheme.specificColorScheme.onSurface,
                content = content,
            )
        }
    }
}

@Composable
fun AppTheme(
    windowSizeClass: WindowSizeClass,
    isDynamicTheme: Boolean = true,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    var colorScheme = when {
        isDynamicTheme -> when {
            darkTheme -> dynamicDarkColorScheme(LocalContext.current)
            else -> dynamicLightColorScheme(LocalContext.current)
        }
        else -> when {
            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }
    }
    val specificColorScheme = when {
        isDynamicTheme -> colorScheme.toSpecificColorScheme(!darkTheme)
        darkTheme -> DefaultDarkSpecificColorScheme
        else -> DefaultLightSpecificColorScheme
    }
    if (isDynamicTheme) colorScheme = colorScheme.copy(outline = specificColorScheme.outline)

    CompositionLocalProvider(
        LocalSpecificColorScheme provides specificColorScheme,
        LocalSpecificTypography provides DefaultSpecificTypography,
        LocalWindowSizeClass provides windowSizeClass,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            shapes = AppShapes,
            typography = DefaultTypography,
            content = { Material2ThemeSupport(darkTheme, content) },
        )
    }
}

object AppTheme {

    val specificColorScheme: SpecificColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalSpecificColorScheme.current

    val specificTypography: SpecificTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalSpecificTypography.current

    val specificIcons = SpecificIcons()

    val specificDimens = DimenValues

    internal val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    internal val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes
}