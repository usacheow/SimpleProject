package com.usacheow.coreui.uikit.information

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.appcompat.widget.LinearLayoutCompat
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.databinding.ViewProgressItemBinding
import com.usacheow.coreui.utils.view.color

class ProgressItemView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr), Populatable<ProgressItem> {

    private val binding by lazy { ViewProgressItemBinding.inflate(LayoutInflater.from(context), this, true) }

    override fun populate(model: ProgressItem) {
        val layoutParams = binding.progressView.layoutParams as LinearLayoutCompat.LayoutParams
        binding.progressView.layoutParams = layoutParams.apply { weight = model.currentValue.toFloat() }
        binding.progressView.backgroundTintList = ColorStateList.valueOf(color(model.colorResId))
        binding.progressLayout.weightSum = model.maxValue.toFloat()
    }
}

data class ProgressItem(
    val maxValue: Int,
    val currentValue: Int,
    @ColorRes val colorResId: Int = R.color.success
)