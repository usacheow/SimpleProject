package com.usacheow.coreui.uikit.organism

import android.content.Context
import android.util.AttributeSet
import android.view.MenuItem
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.core.view.updatePadding
import com.google.android.material.appbar.AppBarLayout
import com.usacheow.coreui.R as CoreUiR
import com.usacheow.coreui.databinding.SimpleAppBarLayoutBinding
import com.usacheow.coreui.uikit.helper.color
import com.usacheow.coreui.uikit.helper.navigation

class SimpleAppBarLayout
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
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

    fun setNavigationAction(
        @DrawableRes iconResId: Int,
        @ColorRes colorId: Int = CoreUiR.color.icon,
        action: () -> Unit,
    ) {
        binding.toolbar.navigation(iconResId, colorId, action)
    }

    fun applyInsets(size: Int) {
        updatePadding(top = size)
    }

    fun inflateMenu(@MenuRes menuResId: Int, listener: (MenuItem) -> Boolean) {
        binding.toolbar.inflateMenu(menuResId)
        binding.toolbar.setOnMenuItemClickListener(listener)
    }
}