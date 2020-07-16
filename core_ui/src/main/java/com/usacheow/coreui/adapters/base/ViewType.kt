package com.usacheow.coreui.adapters.base

abstract class ViewType(
    val layoutId: Int,
    var isShimmer: Boolean = false
)

abstract class RadioViewType(
    layoutId: Int,
    var isSelected: Boolean = false,
    var onSelectAction: () -> Unit = {}
) : ViewType(layoutId)