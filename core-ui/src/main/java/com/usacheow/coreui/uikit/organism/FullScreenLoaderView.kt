package com.usacheow.coreui.uikit.organism

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.databinding.ViewFullScreenLoaderBinding
import com.usacheow.coreui.uikit.helper.color

private const val BACKGROUND_ALPHA = 0.8f

class FullScreenLoaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    private val binding by lazy { ViewFullScreenLoaderBinding.inflate(LayoutInflater.from(context), this) }

    init {
        binding.root.alpha = BACKGROUND_ALPHA
        setBackgroundColor(color(R.color.background))
    }
}