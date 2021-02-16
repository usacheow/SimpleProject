package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.facebook.shimmer.ShimmerFrameLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewSmallInfoCardBinding
import com.usacheow.coreui.utils.view.*

private const val SHIMMER_WIDTH_DP = 120

class SmallInfoCardItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ShimmerFrameLayout(context, attrs, defStyleAttr), Populatable<SmallInfoCardItem> {

    private val binding by lazy { ViewSmallInfoCardBinding.bind(this) }

    override fun populate(model: SmallInfoCardItem) {
        if (model.isShimmer) {
            binding.smallInfoHeaderView.makeInvisible()
            binding.smallInfoValueView.makeInvisible()
            binding.smallInfoCardView.setCardBackgroundColor(color(R.color.colorShimmer))
            resize(widthPx = SHIMMER_WIDTH_DP.toPx, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)

            showShimmer(true)
        } else {
            binding.smallInfoHeaderView.makeVisible()
            binding.smallInfoValueView.makeVisible()
            binding.smallInfoCardView.setCardBackgroundColor(color(R.color.colorGreyCard))

            showData(model)
            hideShimmer()
        }
    }

    private fun showData(model: SmallInfoCardItem) {
        binding.smallInfoHeaderView.populate(model.header)
        binding.smallInfoValueView.text = model.value

        resize(widthPx = when {
            model.widthDp != null -> model.widthDp.toPx
            model.needExpandOnWidth -> ViewGroup.LayoutParams.MATCH_PARENT
            else -> ViewGroup.LayoutParams.WRAP_CONTENT
        }, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.smallInfoClickableView.setListenerIfNeed(model.clickAction)
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