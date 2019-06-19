package com.kapmayn.coreuikit.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class SwipableViewPager
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
    : ViewPager(context, attrs) {

    private var isSwipeEnable = true

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (this.isSwipeEnable) {
            super.onTouchEvent(event)
        } else false

    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (this.isSwipeEnable) {
            super.onInterceptTouchEvent(event)
        } else false

    }

    fun disableSwipe() {
        this.isSwipeEnable = false
    }

    fun enableSwipe() {
        this.isSwipeEnable = true
    }
}