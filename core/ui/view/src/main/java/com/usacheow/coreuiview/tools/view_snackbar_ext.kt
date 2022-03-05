package com.usacheow.coreuiview.tools

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_FADE
import com.google.android.material.snackbar.Snackbar
import com.usacheow.corecommon.container.ColorSource
import com.usacheow.corecommon.container.ImageSource
import com.usacheow.corecommon.container.TextSource
import com.usacheow.coreuiview.tools.resource.ThemeColorsAttrs
import com.usacheow.coreuiview.tools.resource.colorByAttr
import com.usacheow.coreuiview.tools.resource.drawable
import com.usacheow.coreuiview.tools.resource.get
import com.usacheow.coreuiview.tools.resource.toPx
import com.google.android.material.R as MaterialR
import com.usacheow.coreuitheme.R as CoreUiThemeR

private val SNACKBAR_ICON_PADDING_PX = 12.toPx

@SuppressLint("ShowToast")
fun View.makeSnackbar(message: TextSource) = Snackbar
    .make(this, message.get(context), Snackbar.LENGTH_SHORT)
    .apply {
        view.background = drawable(CoreUiThemeR.drawable.bg_snackbar)
    }
    .setAnimationMode(ANIMATION_MODE_FADE)
    .setBackgroundTint(colorByAttr(ThemeColorsAttrs.surfaceInverse))

fun Snackbar.setAction(
    text: TextSource,
    color: ColorSource = ColorSource.fromAttr(ThemeColorsAttrs.symbolPrimary),
    action: () -> Unit,
) = setAction(text.get(context)) { action() }
    .setActionTextColor(color.get(context))

fun Snackbar.setIcon(drawable: ImageSource.Res) = apply {
    view.findViewById<TextView>(MaterialR.id.snackbar_text).apply {
        setCompoundDrawablesWithIntrinsicBounds(drawable(drawable.res), null, null, null)
        compoundDrawablePadding = SNACKBAR_ICON_PADDING_PX
        gravity = Gravity.CENTER_VERTICAL
    }
}
