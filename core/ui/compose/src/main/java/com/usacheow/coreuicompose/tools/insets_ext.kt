package com.usacheow.coreuicompose.tools

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.runtime.Composable
import com.usacheow.coreuicompose.uikit.barcopy.LocalBottomNavigationHeight

@Composable
fun getBottomInset() = WindowInsets.navigationBars
    .union(WindowInsets.ime)
    .asPaddingValues()

@Composable
fun getBottomInsetWithBottomNavigation() = WindowInsets.navigationBars
    .add(WindowInsets(bottom = LocalBottomNavigationHeight.current))
    .union(WindowInsets.ime)
    .asPaddingValues()

@Composable
fun getTopInset() = WindowInsets.statusBars.asPaddingValues()