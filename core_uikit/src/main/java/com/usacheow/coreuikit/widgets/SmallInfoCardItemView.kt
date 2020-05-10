package com.usacheow.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.facebook.shimmer.ShimmerFrameLayout
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.Populatable
import com.usacheow.coreuikit.base.ViewType
import com.usacheow.coreuikit.utils.ext.color
import com.usacheow.coreuikit.utils.ext.makeInvisible
import com.usacheow.coreuikit.utils.ext.makeVisible
import com.usacheow.coreuikit.utils.ext.populate
import com.usacheow.coreuikit.utils.ext.resize
import com.usacheow.coreuikit.utils.ext.setListenerIfNeed
import com.usacheow.coreuikit.utils.ext.toPx
import kotlinx.android.synthetic.main.view_small_info_card.view.smallInfoCardView
import kotlinx.android.synthetic.main.view_small_info_card.view.smallInfoClickableView
import kotlinx.android.synthetic.main.view_small_info_card.view.smallInfoHeaderView
import kotlinx.android.synthetic.main.view_small_info_card.view.smallInfoValueView

private const val SHIMMER_WIDTH_DP = 120

class SmallInfoCardItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ShimmerFrameLayout(context, attrs, defStyleAttr), Populatable<SmallInfoCardItem> {

    override fun populate(model: SmallInfoCardItem) {
        if (model.isShimmer) {
            smallInfoHeaderView.makeInvisible()
            smallInfoValueView.makeInvisible()
            smallInfoCardView.setCardBackgroundColor(color(R.color.colorShimmer))
            resize(widthPx = SHIMMER_WIDTH_DP.toPx, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)

            showShimmer(true)
        } else {
            smallInfoHeaderView.makeVisible()
            smallInfoValueView.makeVisible()
            smallInfoCardView.setCardBackgroundColor(color(R.color.colorGreyCard))

            showData(model)
            hideShimmer()
        }
    }

    private fun showData(model: SmallInfoCardItem) {
        smallInfoHeaderView.populate(model.header)
        smallInfoValueView.text = model.value

        resize(widthPx = when {
            model.widthDp != null -> model.widthDp.toPx
            model.needExpandOnWidth -> ViewGroup.LayoutParams.MATCH_PARENT
            else -> ViewGroup.LayoutParams.WRAP_CONTENT
        }, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)

        smallInfoClickableView.setListenerIfNeed(model.clickAction)
    }
}

data class SmallInfoCardItem(
    val header: String? = null,
    val value: String,
    val widthDp: Int? = null,
    val needExpandOnWidth: Boolean = false,
    val clickAction: (() -> Unit)? = null
) : ViewType(R.layout.view_small_info_card) {

    companion object {
        fun shimmer() = SmallInfoCardItem(value = "").apply { isShimmer = true }
    }
}