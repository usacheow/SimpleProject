package com.usacheow.coreuiview.tools

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar

fun Toolbar.navigation(
    @DrawableRes iconId: Int,
    @ColorInt color: Int,
    listener: () -> Unit,
) {
    setNavigationIcon(iconId)
    setNavigationIconColor(color)
    setNavigationOnClickListener { listener() }
}

fun Toolbar.setNavigationIconColor(@ColorInt color: Int) = navigationIcon?.mutate()?.let {
    it.setTint(color)
    this.navigationIcon = it
}