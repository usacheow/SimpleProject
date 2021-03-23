package com.usacheow.coreui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.usacheow.coreui.R
import com.usacheow.coreui.analytics.AnalyticsTrackerHolder
import com.usacheow.coreui.analytics.Events
import com.usacheow.coreui.base.SimpleLifecycle
import com.usacheow.coreui.delegate.ViewBindingDelegate
import com.usacheow.coreui.delegate.FragmentViewBindingDelegate

abstract class SimpleModalFragment<VIEW_BINDING : ViewBinding> :
    DialogFragment(),
    SimpleLifecycle,
    ViewBindingDelegate<VIEW_BINDING> by FragmentViewBindingDelegate<VIEW_BINDING>() {

    protected abstract val params: Params<VIEW_BINDING>

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
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.ModalDialogTheme
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.ModalDialogTheme)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        saveBinding(params.viewBindingProvider(inflater, container, false))
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        processArguments(arguments)
        setupViews(savedInstanceState)
        subscribe()
    }

    @CallSuper
    override fun onDestroyView() {
        clearViews()
        clearBinding()
        super.onDestroyView()
    }

    data class Params<VIEW_BINDING : ViewBinding>(
        val viewBindingProvider: (LayoutInflater, ViewGroup?, Boolean) -> VIEW_BINDING,
    )
}