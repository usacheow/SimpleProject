package com.usacheow.coreuikit.activity

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.usacheow.core.analytics.AnalyticsTrackerHolder
import com.usacheow.core.analytics.Events
import com.usacheow.diprovider.DiApp
import com.usacheow.diprovider.DiProvider

abstract class SimpleActivity : AppCompatActivity() {

    protected abstract val layoutId: Int
    protected open var needTransparentBars = false

    override fun onStart() {
        super.onStart()
        AnalyticsTrackerHolder.getInstance().trackEvent(Events.START_SCREEN)
    }

    override fun onStop() {
        AnalyticsTrackerHolder.getInstance().trackEvent(Events.STOP_SCREEN)
        super.onStop()
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((application as DiApp).diProvider)

        if (needTransparentBars) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        setContentView(layoutId)
        setupViews(savedInstanceState)
    }

    protected open fun inject(diProvider: DiProvider) = Unit

    protected open fun setupViews(savedInstanceState: Bundle?) = Unit

    override fun onDestroy() {
        clearViews()
        super.onDestroy()
    }

    protected open fun clearViews() = Unit
}