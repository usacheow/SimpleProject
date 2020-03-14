package com.kapmayn.coreuikit.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.kapmayn.core.analytics.AnalyticsTrackerHolder
import com.kapmayn.core.analytics.Events
import com.kapmayn.diproviders.provider.DiApp
import com.kapmayn.diproviders.provider.DiProvider

abstract class SimpleDialogFragment : DialogFragment() {

    protected abstract val layoutId: Int
    protected open var needTransparentBars = true
    protected open var isLightToolbar = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((activity?.application as DiApp).diProvider)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(layoutId, container, false)
        changeStatusBarIconColorIfNeed()
        return rootView
    }

    private fun changeStatusBarIconColorIfNeed() {
        if (!needTransparentBars) return

        val canMakeDarkIcon = isLightToolbar && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        val decor = requireActivity().window.decorView

        if (canMakeDarkIcon) {
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        } else {
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        processArguments(arguments)
        setupViews(savedInstanceState)
        subscribe()
    }

    abstract fun inject(diProvider: DiProvider)

    override fun onStart() {
        super.onStart()
        AnalyticsTrackerHolder.getInstance().trackEvent("start ${this::class.java.simpleName}", Events.APP)
    }

    override fun onStop() {
        AnalyticsTrackerHolder.getInstance().trackEvent("stop ${this::class.java.simpleName}", Events.APP)
        super.onStop()
    }

    protected open fun processArguments(bundle: Bundle?) {}

    protected open fun setupViews(savedInstanceState: Bundle?) {}

    protected open fun subscribe() {}

    protected fun getActivity(action: FragmentActivity.(FragmentActivity) -> Unit) {
        activity?.let { it.action(it) }
    }

    open fun onBackPressed(): Boolean {
        return false
    }
}