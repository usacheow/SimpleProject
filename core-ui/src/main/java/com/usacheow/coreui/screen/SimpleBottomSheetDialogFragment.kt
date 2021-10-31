package com.usacheow.coreui.screen

import android.app.Dialog
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.usacheow.core.resource.ResourcesWrapper
import com.usacheow.coreui.R
import com.google.android.material.R as MaterialR
import com.usacheow.coreui.analytics.AnalyticsTrackerHolder
import com.usacheow.coreui.analytics.Events
import com.usacheow.coreui.screen.base.ApplyWindowInsets
import com.usacheow.coreui.screen.base.FragmentViewBindingHolder
import com.usacheow.coreui.screen.base.ViewBindingHolder
import com.usacheow.coreui.screen.base.SimpleLifecycle
import com.usacheow.coreui.uikit.helper.createWindowInsetsControllerCompat
import com.usacheow.coreui.uikit.helper.doOnApplyWindowInsets
import com.usacheow.coreui.uikit.helper.isNightMode
import com.usacheow.coreui.uikit.helper.toPx
import javax.inject.Inject

abstract class SimpleBottomSheetDialogFragment<VIEW_BINDING : ViewBinding> :
    BottomSheetDialogFragment(),
    SimpleLifecycle,
    ApplyWindowInsets,
    ViewBindingHolder<VIEW_BINDING> by FragmentViewBindingHolder() {

    @Inject lateinit var res: ResourcesWrapper

    protected abstract val defaultParams: Params<VIEW_BINDING>
    protected var windowInsetsController: WindowInsetsControllerCompat? = null

    private val canHide get() = defaultParams.canHide
    private val needWrapContent get() = defaultParams.needWrapContent
    private val needExpand get() = defaultParams.needExpand

    private val middleStatePercent get() = defaultParams.middleStatePercent
    private val needMiddleState get() = defaultParams.needMiddleState

    private val startStatePercent get() = defaultParams.startStatePercent

    private val bottomSheetListener = fun(dialog: DialogInterface) {
        val bottomSheet = (dialog as BottomSheetDialog).findViewById<FrameLayout>(MaterialR.id.design_bottom_sheet)
        bottomSheet?.updateLayoutParams<ViewGroup.LayoutParams> { height = ViewGroup.LayoutParams.MATCH_PARENT }
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)
        bottomSheetBehavior.saveFlags = BottomSheetBehavior.SAVE_ALL

        bottomSheetBehavior.isHideable = canHide

        bottomSheetBehavior.isFitToContents = needWrapContent
        if (needWrapContent) {
            return
        }

        if (needExpand) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        bottomSheetBehavior.peekHeight = getPeekHeight()
        bottomSheetBehavior.halfExpandedRatio = when {
            needMiddleState -> middleStatePercent.divisor
            else -> startStatePercent.divisor
        }
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        AnalyticsTrackerHolder.getInstance()?.trackEvent(Events.START_SCREEN, this.javaClass)
    }

    @CallSuper
    override fun onStop() {
        AnalyticsTrackerHolder.getInstance()?.trackEvent(Events.STOP_SCREEN, this.javaClass)
        super.onStop()
    }

    @CallSuper
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return (super.onCreateDialog(savedInstanceState) as BottomSheetDialog).apply {
            setOnShowListener(bottomSheetListener)
        }
    }

    private fun getPeekHeight(): Int {
        return (startStatePercent.divisor * (Resources.getSystem().displayMetrics.heightPixels - 0.toPx)).toInt()
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        saveBinding(defaultParams.viewBindingProvider(inflater, container, false))
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        windowInsetsController = createWindowInsetsControllerCompat(
            requireActivity().window,
            binding.root,
            isNightMode() || defaultParams.needWhiteStatusIcons,
            isNightMode() || defaultParams.needWhiteNavigationIcons,
        )

        view.doOnApplyWindowInsets(::onApplyWindowInsets)
        setupViews(savedInstanceState)
        subscribe()
    }

    @CallSuper
    override fun onDestroyView() {
        clearViews()
        clearBinding()
        super.onDestroyView()
    }

    data class Params<VIEW_BINDING : ViewBinding>(
        var canHide: Boolean = true,
        var needWrapContent: Boolean = false,
        var needExpand: Boolean = false,

        /*
        * halfExpandedRatio value when needMiddleState
        * */
        var middleStatePercent: BottomDialogHeight = BottomDialogHeight.HALF_SIZE,
        var needMiddleState: Boolean = false,

        /*
        * peekHeight value
        * */
        var startStatePercent: BottomDialogHeight = BottomDialogHeight.QUARTER_SIZE,

        var needWhiteAllIcons: Boolean = false,
        var needWhiteStatusIcons: Boolean = needWhiteAllIcons,
        var needWhiteNavigationIcons: Boolean = needWhiteAllIcons,
        val viewBindingProvider: (LayoutInflater, ViewGroup?, Boolean) -> VIEW_BINDING,
    )

    enum class BottomDialogHeight(val divisor: Float) {

        THREE_QUARTERS_SIZE(0.75f),
        HALF_SIZE(0.5f),
        QUARTER_SIZE(0.25f)
    }
}