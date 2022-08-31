package com.usacheow.coreuicompose.tools

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.union
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp

@Composable
fun insetAll() = WindowInsets.safeDrawing

@Composable
fun insetTop() = WindowInsets.safeDrawing
    .only(WindowInsetsSides.Top)

@Composable
fun insetAllExcludeBottom() = WindowInsets.safeDrawing
    .only(WindowInsetsSides.Start + WindowInsetsSides.End + WindowInsetsSides.Top)

@Composable
fun insetHorizontal() = WindowInsets.safeDrawing
    .only(WindowInsetsSides.Start + WindowInsetsSides.End)

@Composable
fun insetAllExcludeTop() = WindowInsets.safeDrawing
    .only(WindowInsetsSides.Start + WindowInsetsSides.End + WindowInsetsSides.Bottom)

@Composable
fun insetBottom() = WindowInsets.safeDrawing
    .only(WindowInsetsSides.Bottom)
    .union(WindowInsets.ime)

@Composable
fun insetBottomWithBottomNavigation() = WindowInsets.safeDrawing
    .only(WindowInsetsSides.Bottom)
    .union(WindowInsets.ime)
    .add(WindowInsets(bottom = LocalBottomNavigationHeight.current))

val LocalBottomNavigationHeight = staticCompositionLocalOf { 0.dp }

fun Modifier.inset(insets: WindowInsets) = composed { padding(insets.asPaddingValues()) }