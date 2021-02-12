package com.usacheow.coreui.uikit.header

import android.content.Context
import android.util.AttributeSet
import android.view.MenuItem
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import com.google.android.material.appbar.AppBarLayout
import com.usacheow.coreui.databinding.SimpleAppBarLayoutBinding
import com.usacheow.coreui.utils.view.color
import com.usacheow.coreui.utils.view.navigation
import com.usacheow.coreui.utils.view.updateMargins

class SimpleAppBarLayout
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppBarLayout(context, attrs, defStyle) {

    var title: String
        get() = binding.toolbar.title.toString()
        set(value) {
            binding.toolbar.title = value
        }

    private val binding by lazy { SimpleAppBarLayoutBinding.bind(this) }

    fun setBackground(@ColorRes colorId: Int) {
        setBackgroundColor(color(colorId))
    }

    fun setNavigationAction(@DrawableRes iconResId: Int, action: () -> Unit) {
        binding.toolbar.navigation(iconResId, action)
    }

    fun setInset(size: Int) {
        binding.insetView.updateMargins(topPx = size)
        binding.toolbar.updateMargins(topPx = size)
    }

    fun inflateMenu(@MenuRes menuResId: Int, listener: (MenuItem) -> Boolean) {
        binding.toolbar.inflateMenu(menuResId)
        binding.toolbar.setOnMenuItemClickListener(listener)
    }
}
