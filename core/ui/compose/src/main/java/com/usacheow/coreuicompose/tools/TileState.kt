package com.usacheow.coreuicompose.tools

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface TileState {

    @Composable
    fun Content(modifier: Modifier)
}

data class TilesShimmerState(private val block: @Composable (Modifier) -> Unit) : TileState {

    @Composable
    override fun Content(modifier: Modifier) {
        block(modifier)
    }
}