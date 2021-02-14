package com.usacheow.coreui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.usacheow.coreui.analytics.AnalyticsTrackerHolder
import com.usacheow.coreui.analytics.Events
import com.usacheow.coreui.base.SimpleLifecycle

abstract class SimpleActivity<VIEW_BINDING : ViewBinding> : AppCompatActivity(), SimpleLifecycle {

    protected abstract val params: Params<VIEW_BINDING>

    protected val binding: VIEW_BINDING by lazy { viewBindingProvider(layoutInflater) }
    private val viewBindingProvider get() = params.viewBindingProvider

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

        if (needTransparentBars) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        setContentView(binding.root)
        setupViews(savedInstanceState)
        subscribe()
    }

    @CallSuper
    override fun onDestroy() {
        clearViews()
        super.onDestroy()
    }

    //do nothing
    override fun processArguments(bundle: Bundle?) = Unit

    data class Params<VIEW_BINDING : ViewBinding>(
        var needTransparentBars: Boolean = false,
        val viewBindingProvider: (LayoutInflater) -> VIEW_BINDING,
    )
}