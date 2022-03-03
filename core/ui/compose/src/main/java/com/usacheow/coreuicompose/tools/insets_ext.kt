package com.usacheow.coreuicompose.tools

import androidx.compose.runtime.Composable
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

@Composable
fun getAllInsets() = rememberInsetsPaddingValues(insets = LocalWindowInsets.current.systemBars)

@Composable
fun getBottomInset() = rememberInsetsPaddingValues(insets = LocalWindowInsets.current.navigationBars)

@Composable
fun getTopInset() = rememberInsetsPaddingValues(insets = LocalWindowInsets.current.statusBars)