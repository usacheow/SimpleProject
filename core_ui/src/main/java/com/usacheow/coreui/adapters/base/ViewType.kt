package com.usacheow.coreui.adapters.base

import androidx.annotation.LayoutRes

abstract class ViewType(
    @LayoutRes val layoutId: Int,
    var isShimmer: Boolean = false,
)

abstract class TagViewType(
    layoutId: Int,
    var isSelected: Boolean = false,
    var onSelectAction: () -> Unit = {},
) : ViewType(layoutId)