package com.kapmayn.coreuikit.views

import android.content.Context
import android.util.AttributeSet
import android.widget.HorizontalScrollView

class EndFadeEdgeHorizontalScrollView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : HorizontalScrollView(context, attrs, defStyleAttr) {

    override fun getLeftFadingEdgeStrength() = 0f
}