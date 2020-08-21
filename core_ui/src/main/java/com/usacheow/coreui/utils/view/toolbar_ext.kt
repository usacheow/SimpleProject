package com.usacheow.coreui.utils.view

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar

fun Toolbar.navigation(@DrawableRes iconId: Int, listener: () -> Unit) {
    setNavigationIcon(iconId)
    setNavigationOnClickListener { listener() }
}