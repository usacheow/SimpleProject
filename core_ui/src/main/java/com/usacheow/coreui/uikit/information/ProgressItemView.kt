package com.usacheow.coreui.uikit.information

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.appcompat.widget.LinearLayoutCompat
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.utils.ext.color
import kotlinx.android.synthetic.main.view_progress_item.view.progressLayout
import kotlinx.android.synthetic.main.view_progress_item.view.progressView

class ProgressItemView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr), Populatable<ProgressItem> {

    init {
        View.inflate(context, R.layout.view_progress_item, this)
    }

    override fun populate(model: ProgressItem) {
        val layoutParams = progressView.layoutParams as LinearLayoutCompat.LayoutParams
        progressView.layoutParams = layoutParams.apply { weight = model.currentValue.toFloat() }
        progressView.backgroundTintList = ColorStateList.valueOf(color(model.colorResId))
        progressLayout.weightSum = model.maxValue.toFloat()
    }
}

data class ProgressItem(
    val maxValue: Int,
    val currentValue: Int,
    @ColorRes val colorResId: Int = R.color.success
)