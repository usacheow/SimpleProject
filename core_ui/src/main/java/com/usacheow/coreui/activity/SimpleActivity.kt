package com.usacheow.coreui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.viewbinding.ViewBinding
import com.usacheow.coreui.analytics.AnalyticsTrackerHolder
import com.usacheow.coreui.analytics.Events
import com.usacheow.coreui.base.ApplyWindowInsets
import com.usacheow.coreui.base.SimpleLifecycle
import com.usacheow.coreui.delegate.ActivityViewBindingDelegate
import com.usacheow.coreui.delegate.ViewBindingDelegate
import com.usacheow.coreui.utils.view.doOnApplyWindowInsets

abstract class SimpleActivity<VIEW_BINDING : ViewBinding> :
    AppCompatActivity(),
    SimpleLifecycle,
    ApplyWindowInsets,
    ViewBindingDelegate<VIEW_BINDING> by ActivityViewBindingDelegate<VIEW_BINDING>() {

    protected abstract val params: Params<VIEW_BINDING>
    protected var windowInsetsController: WindowInsetsControllerCompat? = null

    private val needTransparentBars get() = params.needTransparentBars

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

        saveBinding(params.viewBindingProvider(layoutInflater))
        setContentView(binding.root)
        binding.root.doOnApplyWindowInsets(::onApplyWindowInsets)

        windowInsetsController = WindowCompat.getInsetsController(window, binding.root).apply {
            this?.isAppearanceLightStatusBars = needTransparentBars
            this?.isAppearanceLightNavigationBars = needTransparentBars
        }

        setupViews(savedInstanceState)
        subscribe()
    }

    @CallSuper
    override fun onDestroy() {
        clearViews()
        super.onDestroy()
    }

    // do nothing
    override fun processArguments(bundle: Bundle?) = Unit

    data class Params<VIEW_BINDING : ViewBinding>(
        var needTransparentBars: Boolean = false,
        val viewBindingProvider: (LayoutInflater) -> VIEW_BINDING,
    )
}