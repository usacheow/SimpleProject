package com.kapmayn.coreui.utils

import android.animation.ValueAnimator
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.android.material.appbar.AppBarLayout

private const val SMALL_ANIMATION_DURATION_IN_MILLIS = 200L
private const val APP_BAR_ELEVATION = 4

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun AppBarLayout.animateElevation(
    toDown: Boolean,
    duration: Long = SMALL_ANIMATION_DURATION_IN_MILLIS
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