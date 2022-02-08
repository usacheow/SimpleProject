package com.usacheow.coreui.uikit.helper

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_FADE
import com.google.android.material.snackbar.Snackbar
import com.usacheow.core.resource.ImageSource
import com.usacheow.core.resource.TextSource
import com.usacheow.core.resource.ColorSource
import com.usacheow.coreui.R as CoreUiR
import com.google.android.material.R as MaterialR

private val SNACKBAR_ICON_PADDING_PX = 12.toPx

@SuppressLint("ShowToast")
fun View.makeSnackbar(message: TextSource) = Snackbar
    .make(this, message.toCharSequence(context), Snackbar.LENGTH_SHORT)
    .apply {
        view.background = drawable(CoreUiR.drawable.bg_snackbar)
    }
    .setAnimationMode(ANIMATION_MODE_FADE)
    .setBackgroundTint(color(CoreUiR.color.surfaceInverse))

fun Snackbar.setAction(
    text: TextSource,
    color: ColorSource = ColorSource.fromRes(CoreUiR.color.symbolPrimary),
    action: () -> Unit,
) = setAction(text.toCharSequence(context)) { action() }
    .setActionTextColor(color.getColorInt(context))

fun Snackbar.setIcon(drawable: ImageSource.Res) = apply {
    view.findViewById<TextView>(MaterialR.id.snackbar_text).apply {
        setCompoundDrawablesWithIntrinsicBounds(drawable(drawable.res), null, null, null)
        compoundDrawablePadding = SNACKBAR_ICON_PADDING_PX
        gravity = Gravity.CENTER_VERTICAL
    }
}
