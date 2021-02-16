package com.usacheow.coreui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.usacheow.coreui.analytics.AnalyticsTrackerHolder
import com.usacheow.coreui.analytics.Events
import com.usacheow.coreui.base.ApplyWindowInsets
import com.usacheow.coreui.base.IBackListener
import com.usacheow.coreui.base.IContainer
import com.usacheow.coreui.base.SimpleLifecycle
import com.usacheow.coreui.delegate.ViewBindingDelegate
import com.usacheow.coreui.utils.view.doOnApplyWindowInsets
import com.usacheow.coreui.utils.view.isNightMode

abstract class SimpleFragment<VIEW_BINDING : ViewBinding> : Fragment(), SimpleLifecycle, ApplyWindowInsets, IBackListener {

    protected abstract val params: Params<VIEW_BINDING>

    protected var bottomDialog: BottomSheetDialog? = null
    protected var messageDialog: AlertDialog? = null

    protected val binding get() = viewBindingDelegate.binding
    private val viewBindingDelegate by lazy { ViewBindingDelegate<VIEW_BINDING>() }
    private val viewBindingProvider get() = params.viewBindingProvider

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
        if (needTransparentBars) {
            val needDarkIcons = !(isNightMode() || needWhiteIcons)
            val canMakeDarkIconInStatusBar = needDarkIcons && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            val canMakeDarkIconInStatusAndNavigationBar = canMakeDarkIconInStatusBar && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

            requireActivity().window.decorView.systemUiVisibility = when {
                canMakeDarkIconInStatusAndNavigationBar -> View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR

                canMakeDarkIconInStatusBar -> View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

                else -> View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            }
        }

        container ?: return null
        viewBindingDelegate.save(viewBindingProvider(inflater, container, false))
        return viewBindingDelegate.rootView
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
        viewBindingDelegate.clear()
        super.onDestroyView()
    }

    fun getContainer(action: IContainer.() -> Unit) {
        if (requireParentFragment() is IContainer) {
            (requireParentFragment() as IContainer).action()
        }
    }

    data class Params<VIEW_BINDING : ViewBinding>(
        var needTransparentBars: Boolean = true,
        var needWhiteIcons: Boolean = false,
        val viewBindingProvider: (LayoutInflater, ViewGroup, Boolean) -> VIEW_BINDING,
    )
}