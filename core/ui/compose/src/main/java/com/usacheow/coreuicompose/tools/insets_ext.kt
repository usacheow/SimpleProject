package com.usacheow.coreuicompose.tools

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.union
import androidx.compose.runtime.Composable
import com.usacheow.coreuicompose.uikit.duplicate.LocalBottomNavigationHeight

@Composable
fun insetAll() = WindowInsets.safeDrawing
    .asPaddingValues()

@Composable
fun insetTop() = WindowInsets.safeDrawing
    .only(WindowInsetsSides.Top)
    .asPaddingValues()

@Composable
fun insetHorizontal() = WindowInsets.safeDrawing
    .only(WindowInsetsSides.Start + WindowInsetsSides.End)
    .asPaddingValues()

@Composable
fun insetAllExcludeTop() = WindowInsets.safeDrawing
    .only(WindowInsetsSides.Start + WindowInsetsSides.End + WindowInsetsSides.Bottom)
    .asPaddingValues()

@Composable
fun insetBottom() = WindowInsets.safeDrawing
    .only(WindowInsetsSides.Bottom)
    .union(WindowInsets.ime)
    .asPaddingValues()

@Composable
fun insetBottomWithBottomNavigation() = WindowInsets.safeDrawing
    .only(WindowInsetsSides.Bottom)
    .union(WindowInsets.ime)
    .add(WindowInsets(bottom = LocalBottomNavigationHeight.current))
    .asPaddingValues()