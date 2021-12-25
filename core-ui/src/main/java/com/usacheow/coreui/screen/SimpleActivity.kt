package com.usacheow.coreui.screen

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.viewbinding.ViewBinding
import com.usacheow.core.resource.ResourcesWrapper
import com.usacheow.coreui.analytics.AnalyticsTrackerHolder
import com.usacheow.coreui.analytics.Events
import com.usacheow.coreui.screen.base.ActivityViewBindingHolder
import com.usacheow.coreui.screen.base.ApplyWindowInsets
import com.usacheow.coreui.screen.base.SimpleLifecycle
import com.usacheow.coreui.screen.base.ViewBindingHolder
import com.usacheow.coreui.uikit.helper.doOnApplyWindowInsets
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

    open fun findNavController(): NavController? = null

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
        binding.root.doOnApplyWindowInsets(::onApplyWindowInsets)

        windowInsetsController = WindowCompat.getInsetsController(window, binding.root)

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