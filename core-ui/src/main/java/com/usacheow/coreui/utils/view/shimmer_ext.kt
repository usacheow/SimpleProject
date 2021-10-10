package com.usacheow.coreui.utils.view

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.facebook.shimmer.ShimmerFrameLayout
import com.usacheow.core.ImageSource
import com.usacheow.coreui.R as CoreUiR

fun ImageView.showCircleShimmer() {
    setImageResource(CoreUiR.drawable.bg_shimmer_circle)
}

fun ImageView.hideShimmer(source: ImageSource) {
    populate(source)
}

fun TextView.showShimmer(widthPx: Int) {
    text = null
    background = drawable(CoreUiR.drawable.bg_shimmer_line)
    resize(widthPx = widthPx, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)
}

fun TextView.hideShimmer(widthPx: Int) {
    background = null
    resize(widthPx = widthPx, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)
}

fun ShimmerFrameLayout.setShimmer(needShimmer: Boolean) = when {
    needShimmer -> showShimmer(true)
    else -> hideShimmer()
}