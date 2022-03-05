package com.usacheow.coreuiview.tools

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