package com.usacheow.coreui.uikit.utils

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.core.widget.ImageViewCompat
import com.makeramen.roundedimageview.RoundedImageView
import com.usacheow.coreui.R
import com.usacheow.coreui.utils.ext.color
import com.usacheow.coreui.utils.ext.load
import com.usacheow.coreui.utils.ext.toPx

private const val ICON_PADDING_DP = 4
private const val IMAGE_PADDING_DP = 0

sealed class ImageInfo(
    val imageUrl: String? = null,
    val imageResId: Int? = null,
    @ColorRes val imageColorResId: Int? = null,
    @DimenRes val imageRadiusResId: Int? = null,
    val isImageVisible: Boolean = true
)

data class ImageState(
    val url: String? = null,
    @DimenRes val radiusResId: Int = R.dimen.radius_16,
    val isVisible: Boolean = true
) : ImageInfo(
    imageUrl = url,
    imageRadiusResId = radiusResId,
    isImageVisible = isVisible,
    imageResId = null,
    imageColorResId = null
)

data class IconState(
    val resId: Int? = null,
    @ColorRes val colorResId: Int? = null,
    @DimenRes val radiusResId: Int = R.dimen.radius_16,
    val isVisible: Boolean = true
) : ImageInfo(
    imageResId = resId,
    imageRadiusResId = radiusResId,
    imageColorResId = colorResId,
    isImageVisible = isVisible,
    imageUrl = null
)

data class EmptyState(
    val isVisible: Boolean = true
) : ImageInfo(
    isImageVisible = isVisible,
    imageUrl = null,
    imageRadiusResId = null,
    imageResId = null,
    imageColorResId = null
)

fun ImageView.populate(state: ImageInfo) {
    when (state) {
        is ImageState -> setupImageState(state)
        is IconState -> setupIconState(state)
        is EmptyState -> setupEmptyState(state)
    }
}

private fun ImageView.setupImageState(state: ImageState) {
    load(state.imageUrl)

    ImageViewCompat.setImageTintList(this, null)
    if (this is RoundedImageView) {
        cornerRadius = state.imageRadiusResId?.let { resources.getDimension(it) } ?: 0f
    }

    val padding = IMAGE_PADDING_DP.toPx
    updatePadding(padding, padding, padding, padding)

    isVisible = state.isImageVisible
}

private fun ImageView.setupIconState(state: IconState) {
    setImageResource(state.imageResId!!)

    val color = state.imageColorResId?.let { ColorStateList.valueOf(color(it)) }
    ImageViewCompat.setImageTintList(this, color)
    if (this is RoundedImageView) {
        cornerRadius = state.imageRadiusResId?.let { resources.getDimension(it) } ?: 0f
    }

    val padding = ICON_PADDING_DP.toPx
    updatePadding(padding, padding, padding, padding)

    isVisible = state.isImageVisible
}

private fun ImageView.setupEmptyState(state: EmptyState) {
    setImageDrawable(null)

    ImageViewCompat.setImageTintList(this, null)
    if (this is RoundedImageView) {
        cornerRadius = 0f
    }

    updatePadding(0, 0, 0, 0)

    isVisible = state.isImageVisible
}