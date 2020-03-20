package com.usacheow.coreuikit.fragments

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.updateLayoutParams
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.usacheow.core.analytics.AnalyticsTrackerHolder
import com.usacheow.core.analytics.Events
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.utils.ext.toPx
import com.usacheow.diprovider.DiApp
import com.usacheow.diprovider.DiProvider

abstract class SimpleBottomSheetDialogFragment : BottomSheetDialogFragment() {

    protected abstract val layoutId: Int
    protected open val needWrapContent = true
    protected open val needMiddleState = false
    protected open val needExpand = false
    protected open val peekHeightPercent = BottomDialogHeight.QUARTER_SIZE
    protected open val halfHeightPercent = BottomDialogHeight.HALF_SIZE

    override fun onStart() {
        super.onStart()
        AnalyticsTrackerHolder.getInstance().trackEvent(Events.START)
    }

    override fun onStop() {
        AnalyticsTrackerHolder.getInstance().trackEvent(Events.STOP)
        super.onStop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((activity?.application as DiApp).diProvider)
    }

    abstract fun inject(diProvider: DiProvider)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        bottomSheetDialog.setOnShowListener { dialog ->
            val bottomSheet = (dialog as BottomSheetDialog).findViewById<FrameLayout>(R.id.design_bottom_sheet)
            bottomSheet?.updateLayoutParams<ViewGroup.LayoutParams> { height = ViewGroup.LayoutParams.MATCH_PARENT }
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)

            bottomSheetBehavior.isFitToContents = needWrapContent
            if (needExpand) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
            if (peekHeightPercent != BottomDialogHeight.WRAP_CONTENT) {
                bottomSheetBehavior.peekHeight = getPeekHeight()
            }
            if (!needWrapContent && (needMiddleState || peekHeightPercent != BottomDialogHeight.FULL_SIZE)) {
                bottomSheetBehavior.halfExpandedRatio = when (needMiddleState) {
                    true -> halfHeightPercent.divisor
                    false -> peekHeightPercent.divisor
                }
            }
        }

        return bottomSheetDialog
    }

    private fun getPeekHeight() = (peekHeightPercent.divisor * (Resources.getSystem().displayMetrics.heightPixels - 0.toPx)).toInt()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        processArguments(arguments)
        setupViews(savedInstanceState)
        subscribe()
    }

    protected open fun processArguments(bundle: Bundle?) {}

    protected open fun setupViews(savedInstanceState: Bundle?) {}

    protected open fun subscribe() {}

    override fun onDestroyView() {
        clearViews()
        super.onDestroyView()
    }

    protected open fun clearViews() {}
}

enum class BottomDialogHeight(val divisor: Float) {

    FULL_SIZE(1.0f),
    THREE_QUARTERS_SIZE(0.75f),
    HALF_SIZE(0.5f),
    QUARTER_SIZE(0.25f),
    WRAP_CONTENT(-1.0f)
}