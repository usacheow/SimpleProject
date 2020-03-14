package com.kapmayn.coreuikit.utils.ext

import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar

fun Toolbar.navigation(@DrawableRes iconId: Int, listener: (View) -> Unit) {
    setNavigationIcon(iconId)
    setNavigationOnClickListener(listener)
}