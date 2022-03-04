package com.usacheow.coreui.screen

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.viewbinding.ViewBinding
import com.usacheow.coreuiview.resourcewrapper.ResourcesWrapper
import com.usacheow.corecommon.analytics.AnalyticsTrackerHolder
import com.usacheow.corecommon.analytics.Events
import com.usacheow.coreui.screen.base.ActivityViewBindingHolder
import com.usacheow.coreui.screen.base.ApplyWindowInsets
import com.usacheow.coreui.screen.base.SimpleLifecycle
import com.usacheow.coreui.screen.base.ViewBindingHolder
import com.usacheow.coreuiview.helper.doOnApplyWindowInsets
import javax.inject.Inject

abstract class SimpleActivity<VIEW_BINDING : ViewBinding> :
    AppCompatActivity(),
    SimpleLifecycle,
    ApplyWindowInsets,
    ViewBindingHolder<VIEW_BINDING> by ActivityViewBindingHolder() {

    @Inject lateinit var res: ResourcesWrapper

    protected abstract val defaultParams: Params<VIEW_BINDING>
    protected var windowInsetsController: WindowInsetsControllerCompat? = null

    protected open fun initSplashScreen() {}

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
        initSplashScreen()

        saveBinding(defaultParams.viewBindingProvider(layoutInflater))
        setContentView(binding.root)

        binding.root.post {
            binding.root.doOnApplyWindowInsets(::onApplyWindowInsets)
            windowInsetsController = ViewCompat.getWindowInsetsController(binding.root)
        }

        setupViews(savedInstanceState)
        subscribe()
    }

    @CallSuper
    override fun onDestroy() {
        clearViews()
        super.onDestroy()
    }

    data class Params<VIEW_BINDING : ViewBinding>(
        val viewBindingProvider: (LayoutInflater) -> VIEW_BINDING,
    )
}