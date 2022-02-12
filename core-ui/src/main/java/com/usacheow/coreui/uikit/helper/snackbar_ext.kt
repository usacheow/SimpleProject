package com.usacheow.coreui.uikit.helper

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_FADE
import com.google.android.material.snackbar.Snackbar
import com.usacheow.core.resource.ColorSource
import com.usacheow.core.resource.ImageSource
import com.usacheow.core.resource.TextSource
import com.google.android.material.R as MaterialR
import com.usacheow.coreui.R as CoreUiR

private val SNACKBAR_ICON_PADDING_PX = 12.toPx

@SuppressLint("ShowToast")
fun View.makeSnackbar(message: TextSource) = Snackbar
    .make(this, message.toCharSequence(context), Snackbar.LENGTH_SHORT)
    .apply {
        view.background = drawable(CoreUiR.drawable.bg_snackbar)
    }
    .setAnimationMode(ANIMATION_MODE_FADE)
    .setBackgroundTint(colorByAttr(ThemeColorsAttrs.surfaceInverse))

fun Snackbar.setAction(
    text: TextSource,
    color: ColorSource = ColorSource.fromAttr(ThemeColorsAttrs.symbolPrimary),
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
