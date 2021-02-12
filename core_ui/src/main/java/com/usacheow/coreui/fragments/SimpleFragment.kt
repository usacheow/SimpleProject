package com.usacheow.coreui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.usacheow.coreui.analytics.AnalyticsTrackerHolder
import com.usacheow.coreui.analytics.Events
import com.usacheow.coreui.base.IBackListener
import com.usacheow.coreui.base.IContainer
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doOnApplyWindowInsets
import com.usacheow.coreui.utils.view.isNightMode

abstract class SimpleFragment<VIEW_BINDING : ViewBinding> : Fragment(), IBackListener {

    private var _binding: VIEW_BINDING? = null
    protected val binding get() = _binding!!

    protected open var needTransparentBars = true
    protected open var needWhiteIcons = false

    protected var bottomDialog: BottomSheetDialog? = null
    protected var messageDialog: AlertDialog? = null

    protected abstract fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): VIEW_BINDING

    override fun onStart() {
        super.onStart()
        AnalyticsTrackerHolder.getInstance()?.trackEvent(Events.START_SCREEN)
    }

    override fun onStop() {
        AnalyticsTrackerHolder.getInstance()?.trackEvent(Events.STOP_SCREEN)
        bottomDialog?.cancel()
        messageDialog?.cancel()
        super.onStop()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        changeSystemIconColorIfNeed()

        _binding = createViewBinding(inflater, container)
        return binding.root
    }

    private fun changeSystemIconColorIfNeed() {
        if (!needTransparentBars) return

        val needDarkIcons = !(isNightMode() || needWhiteIcons)
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

    protected open fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) = Unit

    protected open fun setupViews(savedInstanceState: Bundle?) = Unit

    protected open fun subscribe() = Unit

    override fun onDestroyView() {
        clearViews()
        _binding = null
        super.onDestroyView()
    }

    protected open fun clearViews() = Unit

    override fun onBackPressed() = false

    open fun getSharedViews() = emptyList<View>()

    fun getContainer(action: IContainer.() -> Unit) {
        if (parentFragment is IContainer) {
            (parentFragment as IContainer).action()
        }
    }

    fun getTopLevelContainer(action: IContainer.() -> Unit) {
        if (activity is IContainer) {
            (activity as IContainer).action()
        }
    }
}