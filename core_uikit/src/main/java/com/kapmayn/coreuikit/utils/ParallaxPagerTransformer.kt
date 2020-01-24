package com.kapmayn.coreuikit.utils

import android.view.View
import androidx.viewpager.widget.ViewPager

class ParallaxPagerTransformer(
    val id: Int
) : ViewPager.PageTransformer {

    private val border = 0
    private val speed = .2f

    override fun transformPage(view: View, position: Float) {

        val parallaxView = view.findViewById<View>(id) ?: return

        if (position > -1 && position < 1) {
            val width = parallaxView.width
            parallaxView.translationX = -(position * width * speed)
            val sc = (view.width.toFloat() - border) / view.width
            if (position == .0f) {
                view.scaleX = 1.0f
                view.scaleY = 1.0f
            } else {
                view.scaleX = sc
                view.scaleY = sc
            }
        }
    }
}