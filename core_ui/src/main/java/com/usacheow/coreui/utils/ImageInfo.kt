package com.usacheow.coreui.utils

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.view.isVisible
import androidx.core.widget.ImageViewCompat
import com.makeramen.roundedimageview.RoundedImageView
import com.usacheow.coreui.R
import com.usacheow.coreui.utils.view.color

sealed class ImageInfo(
    val imageSource: ImageSource? = null,
    @ColorRes val imageColorResId: Int? = null,
    val isImageVisible: Boolean = true
)

data class LogoInfo(
    val source: ImageSource? = null,
) : ImageInfo(
    imageSource = source,
    isImageVisible = true,
    imageColorResId = null,
)

data class IconInfo(
    val source: ImageSource? = null,
    @ColorRes val colorResId: Int? = null,
) : ImageInfo(
    imageSource = source,
    imageColorResId = colorResId,
    isImageVisible = true,
)

data class EmptyInfo(
    val isVisible: Boolean = true,
) : ImageInfo(
    isImageVisible = isVisible,
    imageSource = null,
    imageColorResId = null,
)

fun ImageView.apply(info: ImageInfo) {
    populate(info.imageSource)

    val color = info.imageColorResId?.let { ColorStateList.valueOf(color(it)) }
    ImageViewCompat.setImageTintList(this, color)

    isVisible = info.isImageVisible
}