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
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.usacheow.coreuiview.resourcewrapper.ResourcesWrapper
import com.usacheow.corecommon.analytics.AnalyticsTrackerHolder
import com.usacheow.corecommon.analytics.Events
import com.usacheow.coreui.screen.base.ApplyWindowInsets
import com.usacheow.coreui.screen.base.FragmentViewBindingHolder
import com.usacheow.coreui.screen.base.SimpleLifecycle
import com.usacheow.coreui.screen.base.ViewBindingHolder
import com.usacheow.coreuiview.helper.createWindowInsetsControllerCompat
import com.usacheow.coreuiview.helper.doOnApplyWindowInsets
import com.usacheow.coreuiview.helper.isNightMode
import javax.inject.Inject
import com.google.android.material.R as MaterialR

abstract class SimpleBottomSheetDialogFragment<VIEW_BINDING : ViewBinding> :
    BottomSheetDialogFragment(),
    SimpleLifecycle,
    ApplyWindowInsets,
    ViewBindingHolder<VIEW_BINDING> by FragmentViewBindingHolder() {

    @Inject lateinit var res: ResourcesWrapper

    protected abstract val defaultParams: Params<VIEW_BINDING>
    protected var windowInsetsController: WindowInsetsControllerCompat? = null

    private val bottomSheetListener = { dialog: DialogInterface ->
        val bottomSheet = (dialog as BottomSheetDialog).findViewById<FrameLayout>(MaterialR.id.design_bottom_sheet)
        bottomSheet?.updateLayoutParams<ViewGroup.LayoutParams> {
            height = when {
                defaultParams.needWrapContent -> ViewGroup.LayoutParams.WRAP_CONTENT
                else -> ViewGroup.LayoutParams.MATCH_PARENT
            }
        }
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)
        bottomSheetBehavior.saveFlags = BottomSheetBehavior.SAVE_ALL

        bottomSheetBehavior.isHideable = defaultParams.canHide
        bottomSheetBehavior.isFitToContents = defaultParams.needWrapContent

        if (!defaultParams.needWrapContent) {
            if (defaultParams.needExpand) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
            bottomSheetBehavior.peekHeight = getHeightByPercent(defaultParams.startStatePercent.divisor)
            bottomSheetBehavior.halfExpandedRatio = when {
                defaultParams.needMiddleState -> defaultParams.middleStatePercent.divisor
                else -> defaultParams.startStatePercent.divisor
            }
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

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        saveBinding(defaultParams.viewBindingProvider(inflater, container, false))
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.post {
            view.doOnApplyWindowInsets(::onApplyWindowInsets)
            windowInsetsController = createWindowInsetsControllerCompat(
                requireActivity().window,
                binding.root,
                isNightMode() || defaultParams.needWhiteStatusIcons,
                isNightMode() || defaultParams.needWhiteNavigationIcons,
            )
        }
        setupViews(savedInstanceState)
        subscribe()
    }

    @CallSuper
    override fun onDestroyView() {
        clearViews()
        clearBinding()
        super.onDestroyView()
    }

    protected fun getHeightByPercent(percent: Float): Int {
        return (percent * Resources.getSystem().displayMetrics.heightPixels).toInt()
    }

    data class Params<VIEW_BINDING : ViewBinding>(
        var canHide: Boolean = true,
        var needWrapContent: Boolean = false,

        /*
        * the following parameters will work when needWrapContent is false
        * */
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