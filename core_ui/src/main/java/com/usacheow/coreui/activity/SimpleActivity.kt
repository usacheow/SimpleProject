package com.usacheow.coreui.activity

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.usacheow.coreui.analytics.AnalyticsTrackerHolder
import com.usacheow.coreui.analytics.Events

abstract class SimpleActivity(@LayoutRes val layoutId: Int) : AppCompatActivity(layoutId) {

    protected open var needTransparentBars = false

    override fun onStart() {
        super.onStart()
        AnalyticsTrackerHolder.getInstance()?.trackEvent(Events.START_SCREEN)
    }

    override fun onStop() {
        AnalyticsTrackerHolder.getInstance()?.trackEvent(Events.STOP_SCREEN)
        super.onStop()
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (needTransparentBars) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        setContentView(layoutId)
        setupViews(savedInstanceState)
    }

    protected open fun setupViews(savedInstanceState: Bundle?) = Unit

    override fun onDestroy() {
        clearViews()
        super.onDestroy()
    }

    protected open fun clearViews() = Unit
}