package com.usacheow.coreuicompose.tools

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable


@Composable
fun getBottomInset() = WindowInsets.navigationBars.add(WindowInsets.ime).asPaddingValues()

@Composable
fun getTopInset() = WindowInsets.statusBars.asPaddingValues()