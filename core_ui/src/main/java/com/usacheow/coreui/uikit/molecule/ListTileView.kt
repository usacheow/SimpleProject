package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.updatePadding
import com.facebook.shimmer.ShimmerFrameLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewListTileBinding
import com.usacheow.coreui.utils.*
import com.usacheow.coreui.utils.view.*

private const val TOP_DESCRIPTION_SHIMMER_WIDTH_DP = 120
private const val TITLE_SHIMMER_WIDTH_DP = 200
private const val ICON_PADDING_DP = 4
private const val DEFAULT_PADDING_DP = 0

class ListTileView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ShimmerFrameLayout(context, attrs, defStyleAttr), Populatable<ListTileItem> {

    private val binding by lazy { ViewListTileBinding.bind(this) }

    private val topDescriptionShimmerWidthPx by lazy { TOP_DESCRIPTION_SHIMMER_WIDTH_DP.toPx }
    private val titleShimmerWidthPx by lazy { TITLE_SHIMMER_WIDTH_DP.toPx }

    override fun populate(model: ListTileItem) {
        populateTitle(model)
        populateTopDescription(model)
        populateBottomDescription(model)
        populateLeftIcon(model)
        populateRightIcon(model)
        setListenerIfNeed(model.isShimmer, model.onItemClicked)
        setShimmer(model.isShimmer)
    }

    private fun populateTitle(model: ListTileItem) = with (binding.titleView) {
        if (model.isShimmer) {
            showShimmer(widthPx = titleShimmerWidthPx)
        } else {
            hideShimmer(widthPx = ViewGroup.LayoutParams.MATCH_PARENT)
            populate(model.title)
        }
    }

    private fun populateTopDescription(model: ListTileItem) = with (binding.topDescriptionView) {
        if (model.isShimmer) {
            showShimmer(widthPx = topDescriptionShimmerWidthPx)
        } else {
            hideShimmer(widthPx = ViewGroup.LayoutParams.MATCH_PARENT)
            populate(model.topDescription)
        }
    }

    private fun populateBottomDescription(model: ListTileItem) = with (binding.bottomDescriptionView) {
        if (model.isShimmer) {
            makeGone()
        } else {
            populate(model.bottomDescription)
        }
    }

    private fun populateLeftIcon(model: ListTileItem) = with (binding.leftIconView) {
        if (model.isShimmer) {
            showCircleShimmer()
            updatePadding(EmptyInfo())
        } else {
            hideShimmer(model.leftImageInfo)
            updatePadding(model.leftImageInfo)
        }
    }

    private fun populateRightIcon(model: ListTileItem) = with (binding.rightIconView) {
        if (model.isShimmer) {
            makeGone()
        } else {
            apply(model.rightImageInfo)
            updatePadding(model.rightImageInfo)
        }
    }

    private fun ImageView.updatePadding(imageInfo: ImageInfo) {
        val padding = when (imageInfo) {
            is IconInfo -> ICON_PADDING_DP
            else -> DEFAULT_PADDING_DP
        }.toPx
        updatePadding(padding, padding, padding, padding)
    }
}

data class ListTileItem(
    val leftImageInfo: ImageInfo = EmptyInfo(),
    val rightImageInfo: ImageInfo = EmptyInfo(),
    val title: TextSource,
    val topDescription: TextSource? = null,
    val bottomDescription: TextSource? = null,
    val onItemClicked: (() -> Unit)? = null,
) : ViewType(R.layout.view_list_tile) {

    companion object {
        fun shimmer() = ListTileItem(title = TextString("")).apply { isShimmer = true }
    }
}