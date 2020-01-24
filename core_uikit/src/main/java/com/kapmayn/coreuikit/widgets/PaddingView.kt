package com.kapmayn.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.plusAssign
import androidx.core.view.updatePadding
import com.kapmayn.coreuikit.R
import com.kapmayn.coreuikit.base.Populatable
import com.kapmayn.coreuikit.base.ViewType

class PaddingView
@JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attributeSet, defStyleAttr), Populatable<PaddingItem> {

    override fun populate(model: PaddingItem) {
        updatePadding(
            left = model.start,
            right = model.end,
            top = model.top,
            bottom = model.bottom
        )

        val innerView = LayoutInflater.from(context).inflate(model.item.layoutId, null)
        (innerView as Populatable<ViewType>).populate(model.item)

        this += innerView
    }
}

data class PaddingItem(
    val item: ViewType,
    val start: Int = 0,
    val end: Int = 0,
    val top: Int = 0,
    val bottom: Int = 0
) : ViewType(R.layout.view_padding)