package com.usacheow.featurewidget.glance

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun GlanceTheme(
    colors: GlanceColorScheme = GlanceTheme.colors,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalGlanceColorScheme provides colors) {
        content()
    }
}

object GlanceTheme {

    val colors: GlanceColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalGlanceColorScheme.current

    val values: GlanceValues
        get() = DefaultGlanceValues
}