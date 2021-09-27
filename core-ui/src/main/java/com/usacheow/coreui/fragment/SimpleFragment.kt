package com.usacheow.coreui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.app.AlertDialog
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.usacheow.coreui.analytics.AnalyticsTrackerHolder
import com.usacheow.coreui.analytics.Events
import com.usacheow.coreui.base.ApplyWindowInsets
import com.usacheow.coreui.base.SimpleLifecycle
import com.usacheow.coreui.delegate.FragmentViewBindingHolder
import com.usacheow.coreui.delegate.ViewBindingHolder
import com.usacheow.coreui.utils.view.doOnApplyWindowInsets
import com.usacheow.coreui.utils.view.isNightMode

abstract class SimpleFragment<VIEW_BINDING : ViewBinding> :
    Fragment(),
    SimpleLifecycle,
    ApplyWindowInsets,
    ViewBindingHolder<VIEW_BINDING> by FragmentViewBindingHolder() {

    protected abstract val defaultParams: Params<VIEW_BINDING>
    protected var windowInsetsController: WindowInsetsControllerCompat? = null

    protected var bottomDialog: BottomSheetDialog? = null
    protected var messageDialog: AlertDialog? = null

    @CallSuper
    override fun onStart() {
        super.onStart()
        AnalyticsTrackerHolder.getInstance()?.trackEvent(Events.START_SCREEN, this.javaClass)
    }

    @CallSuper
    override fun onStop() {
        AnalyticsTrackerHolder.getInstance()?.trackEvent(Events.STOP_SCREEN, this.javaClass)
        bottomDialog?.cancel()
        messageDialog?.cancel()
        super.onStop()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container ?: return null
        saveBinding(defaultParams.viewBindingProvider(inflater, container, false))

        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
        windowInsetsController = WindowCompat.getInsetsController(requireActivity().window, binding.root).apply {
            val needWhiteIcons = isNightMode() || defaultParams.needWhiteAllIcons
            this?.isAppearanceLightStatusBars = !needWhiteIcons
            this?.isAppearanceLightNavigationBars = !needWhiteIcons
        }

        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.post { view.doOnApplyWindowInsets(::onApplyWindowInsets) }
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
        val viewBindingProvider: (LayoutInflater, ViewGroup, Boolean) -> VIEW_BINDING,
    )
}