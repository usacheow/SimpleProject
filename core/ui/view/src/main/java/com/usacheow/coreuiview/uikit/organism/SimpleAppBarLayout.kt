package com.usacheow.coreuiview.uikit.organism

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.google.android.material.appbar.AppBarLayout
import com.usacheow.coreuiview.databinding.SimpleAppBarLayoutBinding
import com.usacheow.coreuiview.tools.resource.color
import com.usacheow.coreuiview.tools.resource.colorByAttr
import com.usacheow.coreuiview.tools.navigation
import com.usacheow.coreuiview.R as CoreUiViewR
import com.usacheow.coreuitheme.R as CoreUiThemeR

class SimpleAppBarLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : AppBarLayout(context, attrs) {

    val toolbar get() = binding.toolbar

    var title: String
        get() = binding.toolbar.title.toString()
        set(value) {
            binding.toolbar.title = value
        }

    private val binding by lazy { SimpleAppBarLayoutBinding.inflate(LayoutInflater.from(context), this) }

    init {
        context.theme.obtainStyledAttributes(attrs, CoreUiViewR.styleable.SimpleAppBarLayout, 0, 0).apply {
            title = getString(CoreUiViewR.styleable.SimpleAppBarLayout_headerText).orEmpty()
            if (hasValue(CoreUiViewR.styleable.SimpleAppBarLayout_headerColor)) {
                setBackground(color(getResourceId(CoreUiViewR.styleable.SimpleAppBarLayout_headerColor, 0)))
            }
            if (hasValue(CoreUiViewR.styleable.SimpleAppBarLayout_headerNavIcon)) {
                setNavigationAction(
                    getResourceId(CoreUiViewR.styleable.SimpleAppBarLayout_headerNavIcon, 0),
                    getColor(
                        CoreUiViewR.styleable.SimpleAppBarLayout_headerNavIconColor,
                        colorByAttr(CoreUiThemeR.attr.colorSymbolTertiary),
                    ),
                ) {}
            }
        }.recycle()
    }

    fun setBackground(@ColorInt color: Int) {
        setBackgroundColor(color)
    }

    fun setNavigationAction(
        @DrawableRes iconResId: Int,
        @ColorInt color: Int = colorByAttr(CoreUiThemeR.attr.colorSymbolPrimary),
        action: () -> Unit,
    ) {
        binding.toolbar.navigation(iconResId, color, action)
    }

    fun setNavigationAction(action: () -> Unit) {
        binding.toolbar.setNavigationOnClickListener { action() }
    }
}