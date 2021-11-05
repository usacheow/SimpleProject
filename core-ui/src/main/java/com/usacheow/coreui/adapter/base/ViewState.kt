package com.usacheow.coreui.adapter.base

import androidx.annotation.LayoutRes
import androidx.compose.runtime.Composable

abstract class ViewState(
    @LayoutRes val layoutId: Int,
    var isShimmer: Boolean = false,
)

abstract class TagViewState(
    layoutId: Int,
    var isSelected: Boolean = false,
    var clickListener: () -> Unit = {},
) : ViewState(layoutId)

abstract class WidgetState(
    val Draw: @Composable () -> Unit,
)

data class ShimmerState(private val content: @Composable () -> Unit) : WidgetState(content)