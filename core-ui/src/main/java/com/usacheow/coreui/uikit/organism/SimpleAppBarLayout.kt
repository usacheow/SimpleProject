package com.usacheow.coreui.uikit.organism

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import com.google.android.material.appbar.AppBarLayout
import com.usacheow.coreui.databinding.SimpleAppBarLayoutBinding
import com.usacheow.coreui.uikit.helper.color
import com.usacheow.coreui.uikit.helper.drawable
import com.usacheow.coreui.uikit.helper.navigation
import com.usacheow.coreui.R as CoreUiR

class SimpleAppBarLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : AppBarLayout(context, attrs) {

    var title: String
        get() = binding.toolbar.title.toString()
        set(value) {
            binding.toolbar.title = value
        }

    private val binding by lazy { SimpleAppBarLayoutBinding.inflate(LayoutInflater.from(context), this) }

    init {
        context.theme.obtainStyledAttributes(attrs, CoreUiR.styleable.SimpleAppBarLayout, 0, 0).apply {
            title = getString(CoreUiR.styleable.SimpleAppBarLayout_headerText).orEmpty()
            if (hasValue(CoreUiR.styleable.SimpleAppBarLayout_headerColor)) {
                setBackground(getResourceId(CoreUiR.styleable.SimpleAppBarLayout_headerColor, 0))
            }
            if (hasValue(CoreUiR.styleable.SimpleAppBarLayout_headerNavIcon)) {
                setNavigationAction(
                    getResourceId(CoreUiR.styleable.SimpleAppBarLayout_headerNavIcon, 0),
                    getResourceId(CoreUiR.styleable.SimpleAppBarLayout_headerNavIconColor, CoreUiR.color.icon),
                ) {}
            }
        }.recycle()
    }

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

    fun setNavigationAction(action: () -> Unit) {
        binding.toolbar.setNavigationOnClickListener { action() }
    }
}