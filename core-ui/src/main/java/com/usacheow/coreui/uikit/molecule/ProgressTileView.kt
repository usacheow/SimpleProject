package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.appcompat.widget.LinearLayoutCompat
import com.usacheow.core.resource.ColorSource
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.databinding.ViewProgressTileBinding
import com.usacheow.coreui.uikit.helper.ThemeColorsAttrs
import com.usacheow.coreui.uikit.helper.color
import com.usacheow.coreui.uikit.helper.getColorInt
import com.usacheow.coreui.R as CoreUiR

class ProgressTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs), Populatable<ProgressTileItem> {

    private val binding by lazy { ViewProgressTileBinding.inflate(LayoutInflater.from(context), this, true) }

    override fun populate(model: ProgressTileItem) {
        val layoutParams = binding.progressView.layoutParams as LinearLayoutCompat.LayoutParams
        binding.progressView.layoutParams = layoutParams.apply { weight = model.currentValue.toFloat() }
        binding.progressView.backgroundTintList = ColorStateList.valueOf(model.color.getColorInt(context))
        binding.progressLayout.weightSum = model.maxValue.toFloat()
    }
}

data class ProgressTileItem(
    val maxValue: Int,
    val currentValue: Int,
    val color: ColorSource = ColorSource.fromAttr(ThemeColorsAttrs.primary),
)