package com.usacheow.coreuiview.organism

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.usacheow.coreuiview.databinding.ViewFullScreenLoaderBinding
import com.usacheow.coreuiview.helper.ThemeColorsAttrs
import com.usacheow.coreuiview.helper.colorByAttr

private const val BACKGROUND_ALPHA = 0.8f

class FullScreenLoaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    private val binding by lazy { ViewFullScreenLoaderBinding.inflate(LayoutInflater.from(context), this) }

    init {
        binding.root.alpha = BACKGROUND_ALPHA
        setBackgroundColor(colorByAttr(ThemeColorsAttrs.background))
    }
}