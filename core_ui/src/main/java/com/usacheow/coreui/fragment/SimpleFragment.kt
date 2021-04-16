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
import com.usacheow.coreui.base.BackListener
import com.usacheow.coreui.base.Container
import com.usacheow.coreui.base.SimpleLifecycle
import com.usacheow.coreui.delegate.FragmentViewBindingDelegate
import com.usacheow.coreui.delegate.ViewBindingDelegate
import com.usacheow.coreui.utils.view.doOnApplyWindowInsets
import com.usacheow.coreui.utils.view.isNightMode

abstract class SimpleFragment<VIEW_BINDING : ViewBinding> :
    Fragment(),
    SimpleLifecycle,
    ApplyWindowInsets,
    BackListener,
    ViewBindingDelegate<VIEW_BINDING> by FragmentViewBindingDelegate<VIEW_BINDING>() {

    protected abstract val params: Params<VIEW_BINDING>
    protected var windowInsetsController: WindowInsetsControllerCompat? = null

    protected var bottomDialog: BottomSheetDialog? = null
    protected var messageDialog: AlertDialog? = null

    private val needTransparentBars get() = params.needTransparentBars
    private val needWhiteIcons get() = params.needWhiteIcons

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
        saveBinding(params.viewBindingProvider(inflater, container, false))

        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
        windowInsetsController = WindowCompat.getInsetsController(requireActivity().window, binding.root).apply {
            val needDarkIcons = needTransparentBars && !(isNightMode() || needWhiteIcons)
            this?.isAppearanceLightStatusBars = needDarkIcons
            this?.isAppearanceLightNavigationBars = needDarkIcons
        }

        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        processArguments(arguments)
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

    fun getContainer(action: Container.() -> Unit) {
        val container = parentFragment ?: activity
        if (container is Container) {
            container.action()
        }
    }

    data class Params<VIEW_BINDING : ViewBinding>(
        var needTransparentBars: Boolean = true,
        var needWhiteIcons: Boolean = false,
        val viewBindingProvider: (LayoutInflater, ViewGroup, Boolean) -> VIEW_BINDING,
    )
}