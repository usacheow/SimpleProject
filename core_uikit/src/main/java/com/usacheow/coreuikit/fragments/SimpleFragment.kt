package com.usacheow.coreuikit.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.usacheow.core.analytics.AnalyticsTrackerHolder
import com.usacheow.core.analytics.Events
import com.usacheow.coreuikit.base.IBackListener
import com.usacheow.coreuikit.base.IContainer
import com.usacheow.coreuikit.utils.ext.PaddingValue
import com.usacheow.coreuikit.utils.ext.doOnApplyWindowInsets
import com.usacheow.coreuikit.utils.ext.isDarkMode
import com.usacheow.diprovider.DiApp
import com.usacheow.diprovider.DiProvider

abstract class SimpleFragment : Fragment(), IBackListener {

    protected abstract val layoutId: Int
    protected open var needTransparentBars = true
    protected open var needForceDarkIcons = true

    protected var bottomDialog: BottomSheetDialog? = null
    protected var messageDialog: AlertDialog? = null

    override fun onStart() {
        super.onStart()
        AnalyticsTrackerHolder.getInstance().trackEvent(Events.START_SCREEN)
    }

    override fun onStop() {
        AnalyticsTrackerHolder.getInstance().trackEvent(Events.STOP_SCREEN)
        bottomDialog?.cancel()
        messageDialog?.cancel()
        super.onStop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((activity?.application as DiApp).diProvider)
    }

    protected open fun inject(diProvider: DiProvider) = Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(layoutId, container, false)
        changeSystemIconColorIfNeed()
        return rootView
    }

    private fun changeSystemIconColorIfNeed() {
        if (!needTransparentBars) return

        val needDarkIcons = !isDarkMode() && needForceDarkIcons
        val canMakeDarkIconInStatusBar = needDarkIcons && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        val canMakeDarkIconInStatusAndNavigationBar = canMakeDarkIconInStatusBar && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

        requireActivity().window.decorView.systemUiVisibility = when {
            canMakeDarkIconInStatusAndNavigationBar ->
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            canMakeDarkIconInStatusBar ->
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            else ->
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        processArguments(arguments)
        view.doOnApplyWindowInsets(::onApplyWindowInsets)
        setupViews(savedInstanceState)
        subscribe()
    }

    protected open fun processArguments(bundle: Bundle?) = Unit

    protected open fun setupViews(savedInstanceState: Bundle?) = Unit

    protected open fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {}

    protected open fun subscribe() = Unit

    override fun onDestroyView() {
        clearViews()
        super.onDestroyView()
    }

    protected open fun clearViews() = Unit

    protected fun getActivity(action: FragmentActivity.(FragmentActivity) -> Unit) {
        activity?.let { it.action(it) }
    }

    fun getContainer(action: IContainer.(IContainer) -> Unit) {
        val container = when {
            parentFragment is IContainer -> parentFragment as IContainer
            activity is IContainer -> activity as IContainer
            else -> null
        }

        container?.let { it.action(it) }
    }

    override fun onBackPressed() = false

    open fun getSharedViews() = emptyList<View>()
}