package com.usacheow.coreui.uikit.header

import android.content.Context
import android.util.AttributeSet
import android.view.MenuItem
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import com.google.android.material.appbar.AppBarLayout
import com.usacheow.coreui.utils.view.color
import com.usacheow.coreui.utils.view.navigation
import com.usacheow.coreui.utils.view.updateMargins
import kotlinx.android.synthetic.main.simple_toolbar.view.insetView
import kotlinx.android.synthetic.main.simple_toolbar.view.toolbar

class SimpleAppBarLayout
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppBarLayout(context, attrs, defStyle) {

    var title: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun setBackground(@ColorRes colorId: Int) {
        setBackgroundColor(color(colorId))
    }

    fun setNavigationAction(@DrawableRes iconResId: Int, action: () -> Unit) {
        toolbar.navigation(iconResId, action)
    }

    fun setInset(size: Int) {
        insetView.updateMargins(topPx = size)
        toolbar.updateMargins(topPx = size)
    }

    fun inflateMenu(@MenuRes menuResId: Int, listener: (MenuItem) -> Boolean) {
        toolbar.inflateMenu(menuResId)
        toolbar.setOnMenuItemClickListener(listener)
    }
}
