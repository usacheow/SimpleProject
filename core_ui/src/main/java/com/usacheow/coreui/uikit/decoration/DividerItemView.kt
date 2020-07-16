package com.usacheow.coreui.uikit.decoration

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.utils.ext.color
import com.usacheow.coreui.utils.ext.resize
import com.usacheow.coreui.utils.ext.toPx
import com.usacheow.coreui.utils.ext.updateMargins
import kotlinx.android.synthetic.main.divider_item_view.view.dividerBackgroundView

class DividerItemView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), Populatable<DividerItem> {

    override fun populate(model: DividerItem) {
        dividerBackgroundView.setBackgroundColor(color(model.colorResId))
        dividerBackgroundView.resize(
            widthPx = ViewGroup.LayoutParams.MATCH_PARENT,
            heightPx = resources.getDimension(model.heightResId).toInt()
        )
        dividerBackgroundView.updateMargins(
            leftPx = model.startMarginDp.toPx,
            rightPx = model.endMarginDp.toPx
        )
    }
}

data class DividerItem(
    val startMarginDp: Int = 0,
    val endMarginDp: Int = 0,
    @DimenRes val heightResId: Int = R.dimen.divider_height_small,
    @ColorRes var colorResId: Int = R.color.colorDivider
) : ViewType(R.layout.divider_item_view) {

    companion object {

        fun getSmallDivider() = DividerItem()

        fun getBigDivider() = DividerItem(heightResId = R.dimen.divider_height_big)

    }
}