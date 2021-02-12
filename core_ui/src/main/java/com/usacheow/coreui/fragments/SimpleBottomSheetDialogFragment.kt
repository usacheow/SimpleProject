package com.usacheow.coreui.fragments

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.updateLayoutParams
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.usacheow.coreui.R
import com.usacheow.coreui.analytics.AnalyticsTrackerHolder
import com.usacheow.coreui.analytics.Events
import com.usacheow.coreui.utils.view.toPx

abstract class SimpleBottomSheetDialogFragment<VIEW_BINDING : ViewBinding>  : BottomSheetDialogFragment() {

    private var _binding: VIEW_BINDING? = null
    protected val binding get() = _binding!!

    protected open val canHide = true

    protected open val needWrapContent = false

    protected open val needExpand = false

    /*
    * halfExpandedRatio value when needMiddleState
    * */
    protected open val middleStatePercent = BottomDialogHeight.HALF_SIZE
    protected open val needMiddleState = false

    /*
    * peekHeight value
    * */
    protected open val startStatePercent = BottomDialogHeight.QUARTER_SIZE

    protected abstract fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): VIEW_BINDING

    override fun onStart() {
        super.onStart()
        AnalyticsTrackerHolder.getInstance()?.trackEvent(Events.START_SCREEN)
    }

    override fun onStop() {
        AnalyticsTrackerHolder.getInstance()?.trackEvent(Events.STOP_SCREEN)
        super.onStop()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        bottomSheetDialog.setOnShowListener { dialog ->
            val bottomSheet = (dialog as BottomSheetDialog).findViewById<FrameLayout>(R.id.design_bottom_sheet)
            bottomSheet?.updateLayoutParams<ViewGroup.LayoutParams> { height = ViewGroup.LayoutParams.MATCH_PARENT }
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)

            bottomSheetBehavior.isHideable = canHide

            bottomSheetBehavior.isFitToContents = needWrapContent
            if (needWrapContent) return@setOnShowListener

            if (needExpand) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
            bottomSheetBehavior.peekHeight = getPeekHeight()
            bottomSheetBehavior.halfExpandedRatio = when {
                needMiddleState -> middleStatePercent.divisor
                else -> startStatePercent.divisor
            }
        }

        return bottomSheetDialog
    }

    private fun getPeekHeight() = (startStatePercent.divisor * (Resources.getSystem().displayMetrics.heightPixels - 0.toPx)).toInt()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = createViewBinding(inflater, container)
        return binding.root
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
        _binding = null
        super.onDestroyView()
    }

    protected open fun clearViews() {}
}

enum class BottomDialogHeight(val divisor: Float) {

    THREE_QUARTERS_SIZE(0.75f),
    HALF_SIZE(0.5f),
    QUARTER_SIZE(0.25f)
}