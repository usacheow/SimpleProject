package com.usacheow.apptest.details

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.usacheow.coreui.utils.view.toPx

class DetailsBottomSheetLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val isExpanded: Boolean
        get() = with(bottomSheetBehavior) {
            state == BottomSheetBehavior.STATE_EXPANDED
        }
    val isCollapsed: Boolean
        get() = with(bottomSheetBehavior) {
            state == BottomSheetBehavior.STATE_COLLAPSED
        }
    val isVisible: Boolean
        get() = with(bottomSheetBehavior) {
            state != BottomSheetBehavior.STATE_HIDDEN
        }

    private var canSwipeToHide = false
    private val bottomSheetBehavior by lazy { BottomSheetBehavior.from(this) }

    init {
        isClickable = true
    }

    fun setScrollEnable(isEnable: Boolean) {
        (bottomSheetBehavior as LockableBottomSheetBehavior).swipeEnabled = isEnable
    }

    fun setup(
        percent: Float,
        canSwipeToHide: Boolean,
        onStateChangedListener: (newState: Int) -> Unit = {},
        onScrollListener: (offset: Float) -> Unit = {},
    ) {
        this.canSwipeToHide = false

        with(bottomSheetBehavior) {
            peekHeight = getHeight(percent)
            halfExpandedRatio = percent
            isHideable = canSwipeToHide
            isFitToContents = false
            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) = onStateChangedListener(newState)

                override fun onSlide(bottomSheet: View, slideOffset: Float) = onScrollListener(slideOffset)
            })
        }
    }

    fun getHeight(percent: Float) =
        (percent * (Resources.getSystem().displayMetrics.heightPixels - 0.toPx)).toInt()

    fun setExpandOffset(heightPx: Int) = with(bottomSheetBehavior) {
        expandedOffset = heightPx
    }

    fun setExpandState() = with(bottomSheetBehavior) {
        isHideable = canSwipeToHide
        state = BottomSheetBehavior.STATE_EXPANDED
    }

    fun setCollapseState() = with(bottomSheetBehavior) {
        isHideable = canSwipeToHide
        state = BottomSheetBehavior.STATE_COLLAPSED
    }

    fun setHiddenState() = with(bottomSheetBehavior) {
        isHideable = true
        state = BottomSheetBehavior.STATE_HIDDEN
    }

    enum class BottomSheetHeight(val divisor: Float) {

        THREE_QUARTERS_SIZE(0.75f),
        HALF_SIZE(0.5f),
        QUARTER_SIZE(0.25f)
    }

}

class LockableBottomSheetBehavior<V : View> : BottomSheetBehavior<V> {
    constructor() : super()
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    var swipeEnabled = true

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: V,
        event: MotionEvent
    ): Boolean {
        return if (swipeEnabled) {
            super.onInterceptTouchEvent(parent, child, event)
        } else {
            false
        }
    }

    override fun onTouchEvent(parent: CoordinatorLayout, child: V, event: MotionEvent): Boolean {
        return if (swipeEnabled) {
            super.onTouchEvent(parent, child, event)
        } else {
            false
        }
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return if (swipeEnabled) {
            super.onStartNestedScroll(
                coordinatorLayout,
                child,
                directTargetChild,
                target,
                axes,
                type
            )
        } else {
            false
        }
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        if (swipeEnabled) {
            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        }
    }

    override fun onStopNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        target: View,
        type: Int
    ) {
        if (swipeEnabled) {
            super.onStopNestedScroll(coordinatorLayout, child, target, type)
        }
    }

    override fun onNestedPreFling(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        target: View,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return if (swipeEnabled) {
            super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY)
        } else {
            false
        }
    }
}