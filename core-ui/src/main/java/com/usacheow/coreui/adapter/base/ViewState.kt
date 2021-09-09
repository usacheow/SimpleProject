package com.usacheow.coreui.adapter.base

import androidx.annotation.LayoutRes

abstract class ViewState(
    @LayoutRes val layoutId: Int,
    var isShimmer: Boolean = false,
)

abstract class TagViewState(
    layoutId: Int,
    var isSelected: Boolean = false,
    var clickListener: () -> Unit = {},
) : ViewState(layoutId)