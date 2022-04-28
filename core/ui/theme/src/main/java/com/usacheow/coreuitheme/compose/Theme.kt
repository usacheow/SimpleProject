package com.usacheow.coreuitheme.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val specificColorScheme = when {
        darkTheme -> DarkSpecificColorScheme
        else -> LightSpecificColorScheme
    }
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(
        LocalSpecificColorScheme provides specificColorScheme,
        LocalSpecificTypography provides DefaultSpecificTypography,
        LocalSpecificIcons provides SpecificIcons(),
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

    val specificIcons: SpecificIcons
        @Composable
        @ReadOnlyComposable
        get() = LocalSpecificIcons.current

    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes
}