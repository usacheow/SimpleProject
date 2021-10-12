package com.usacheow.coreui.uikit.helper

import android.graphics.drawable.GradientDrawable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt

fun Int.gradientTo(
    direction: GradientDrawable.Orientation,
    vararg colors: Int,
) = GradientDrawable(direction, intArrayOf(this) + colors.toTypedArray().toIntArray())

fun String.colorify(
    coloredPartLength: Int,
    @ColorInt color: Int,
) = SpannableString(this).apply {
    val colorSpan = ForegroundColorSpan(color)
    val startIndex = this.length - coloredPartLength
    val endIndex = this.length
    setSpan(colorSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
}