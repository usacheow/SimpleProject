package com.kapmayn.core.presentation.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.kapmayn.core.analytics.AnalyticsTrackerHolder
import com.kapmayn.core.analytics.Events
import com.kapmayn.diproviders.provider.DiApp
import com.kapmayn.diproviders.provider.DiProvider

abstract class SimpleActivity : AppCompatActivity() {

    protected abstract val layoutId: Int

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((application as DiApp).diProvider)
        setContentView(layoutId)
        setupViews(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        AnalyticsTrackerHolder.getInstance().trackEvent("onStart = ${this::class.java.simpleName}", Events.APP)
    }

    override fun onStop() {
        AnalyticsTrackerHolder.getInstance().trackEvent("onStop = ${this::class.java.simpleName}", Events.APP)
        super.onStop()
    }

    abstract fun inject(diProvider: DiProvider)

    protected open fun setupViews(savedInstanceState: Bundle?) {}
}