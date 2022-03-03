package com.usacheow.coreuicompose.tools

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface WidgetState {

    @Composable
    fun Content(modifier: Modifier = Modifier) {}
}

data class ShimmerState(private val block: @Composable () -> Unit) : WidgetState {

    @Composable
    override fun Content(modifier: Modifier) {
        block()
    }
}