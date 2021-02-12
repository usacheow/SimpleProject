package com.usacheow.coreui.uikit.listitem

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.facebook.shimmer.ShimmerFrameLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewListTileItemBinding
import com.usacheow.coreui.uikit.utils.EmptyState
import com.usacheow.coreui.uikit.utils.ImageInfo
import com.usacheow.coreui.uikit.utils.populate
import com.usacheow.coreui.utils.view.*

private const val SUBTITLE_SHIMMER_WIDTH_DP = 120
private const val TITLE_SHIMMER_WIDTH_DP = 200

class ListTileItemView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ShimmerFrameLayout(context, attrs, defStyleAttr), Populatable<ListTileItem> {

    private val binding by lazy { ViewListTileItemBinding.bind(this) }

    override fun populate(model: ListTileItem) {
        if (model.isShimmer) {
            binding.actionTitleView.setBackgroundResource(R.drawable.bg_shimmer_line)
            binding.actionTitleView.resize(widthPx = TITLE_SHIMMER_WIDTH_DP.toPx, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)
            binding.actionSubtitleView.setBackgroundResource(R.drawable.bg_shimmer_line)
            binding.actionSubtitleView.resize(widthPx = SUBTITLE_SHIMMER_WIDTH_DP.toPx, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)
            binding.actionIconView.setImageResource(R.drawable.bg_shimmer_circle)
            binding.actionDescriptionView.makeGone()

            showShimmer(true)
        } else {
            binding.actionTitleView.background = null
            binding.actionTitleView.resize(widthPx = ViewGroup.LayoutParams.MATCH_PARENT, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)
            binding.actionSubtitleView.background = null
            binding.actionSubtitleView.resize(widthPx = ViewGroup.LayoutParams.MATCH_PARENT, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)
            binding.actionIconView.setImageDrawable(null)
            binding.actionDescriptionView.makeVisible()

            hideShimmer()
            showData(model)
        }
    }

    private fun showData(model: ListTileItem) {
        binding.actionTitleView.text = model.title
        binding.actionSubtitleView.populate(model.subtitle)
        binding.actionDescriptionView.populate(model.description)
        binding.actionIconView.populate(model.imageInfo)
        setListenerIfNeed(model.onItemClicked)
    }
}

data class ListTileItem(
    val imageInfo: ImageInfo = EmptyState(),
    val title: String? = null,
    val subtitle: String,
    val description: String? = null,
    val onItemClicked: (() -> Unit)? = null
) : ViewType(R.layout.view_list_tile_item) {

    companion object {
        fun shimmer() = ListTileItem(subtitle = "").apply { isShimmer = true }
    }
}