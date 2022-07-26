package com.usacheow.coreuicompose.tools

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface TileState {

    @Composable
    fun Content(modifier: Modifier)
}