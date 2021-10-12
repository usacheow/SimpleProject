package com.usacheow.coreui.uikit.template

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Outline
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.usacheow.coreui.R as CoreUiR
import com.usacheow.coreui.uikit.helper.color
import com.usacheow.coreui.uikit.helper.dimen
import com.usacheow.coreui.uikit.helper.toPx

class SimpleBottomSheetLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    val isExpanded: Boolean
        get() = with(bottomSheetBehavior) {
            state == BottomSheetBehavior.STATE_EXPANDED
        }
    val isVisible: Boolean
        get() = with(bottomSheetBehavior) {
            state != BottomSheetBehavior.STATE_HIDDEN
        }

    private val cornerRadiusPx by lazy { dimen(CoreUiR.dimen.radius_large) }

    private var percent = BottomSheetHeight.QUARTER_SIZE.divisor
    private var canSwipeToHide = false
    private val bottomSheetBehavior by lazy { BottomSheetBehavior.from<View>(this) }

    init {
        setBackgroundColor(color(CoreUiR.color.background))
        isClickable = true
    }

    override fun onDraw(canvas: Canvas?) {
        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                view ?: return
                outline?.setRoundRect(0, 0, view.width, (view.height + cornerRadiusPx).toInt(), cornerRadiusPx)
            }
        }

        clipToOutline = true
    }

    fun setup(
        percent: BottomSheetHeight,
        canSwipeToHide: Boolean,
        onStateChangedListener: (newState: Int) -> Unit = {},
        onScrollListener: (offset: Float) -> Unit = {},
    ) {
        this.percent = percent.divisor
        this.canSwipeToHide = canSwipeToHide

        with(bottomSheetBehavior) {
            peekHeight = getHeight(percent.divisor)
            halfExpandedRatio = percent.divisor
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
        expandedOffset = heightPx - cornerRadiusPx.toInt()
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