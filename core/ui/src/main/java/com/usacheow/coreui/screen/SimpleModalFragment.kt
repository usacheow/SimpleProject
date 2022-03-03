package com.usacheow.coreui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.usacheow.corecommon.resource.ResourcesWrapper
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
import com.usacheow.coreuitheme.R as CoreUiThemeR

abstract class SimpleModalFragment<VIEW_BINDING : ViewBinding> :
    DialogFragment(),
    SimpleLifecycle,
    ApplyWindowInsets,
    ViewBindingHolder<VIEW_BINDING> by FragmentViewBindingHolder() {

    @Inject lateinit var res: ResourcesWrapper

    protected abstract val defaultParams: Params<VIEW_BINDING>
    protected var windowInsetsController: WindowInsetsControllerCompat? = null

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, CoreUiThemeR.style.Simple_AppTheme)
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        saveBinding(defaultParams.viewBindingProvider(inflater, container, false))
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = CoreUiThemeR.style.Simple_Dialog_ModalDialogAnimation

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

    data class Params<VIEW_BINDING : ViewBinding>(
        var needWhiteAllIcons: Boolean = false,
        var needWhiteStatusIcons: Boolean = needWhiteAllIcons,
        var needWhiteNavigationIcons: Boolean = needWhiteAllIcons,
        val viewBindingProvider: (LayoutInflater, ViewGroup?, Boolean) -> VIEW_BINDING,
    )
}