package com.usacheow.coreui.utils.view

import android.animation.ValueAnimator
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.usacheow.coreui.R as CoreUiR

private const val SMALL_ANIMATION_DURATION_IN_MILLIS = 200L
private const val APP_BAR_ELEVATION = 4

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun AppBarLayout.animateElevation(
    toDown: Boolean,
    duration: Long = SMALL_ANIMATION_DURATION_IN_MILLIS,
) {
    val (from, to) = when {
        toDown -> Pair(elevation, 0f)
        else -> Pair(0f, APP_BAR_ELEVATION.toPx.toFloat())
    }

    ValueAnimator.ofFloat(from, to)
        .apply {
            this.duration = duration
            startDelay = 0
            addUpdateListener { elevation = it.animatedValue as Float }
            start()
        }
}

fun Toolbar.navigation(@DrawableRes iconId: Int, @ColorRes colorId: Int = CoreUiR.color.icon, listener: () -> Unit) {
    setNavigationIcon(iconId)
    setNavigationIconColor(colorId)
    setNavigationOnClickListener { listener() }
}

fun Toolbar.setNavigationIconColor(@ColorRes colorId: Int) = navigationIcon?.mutate()?.let {
    it.setTint(color(colorId))
    this.navigationIcon = it
}