package com.usacheow.coreui.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.usacheow.coreui.R
import com.usacheow.coreui.analytics.AnalyticsTrackerHolder
import com.usacheow.coreui.analytics.Events

abstract class SimpleModalFragment : DialogFragment() {

    protected abstract val layoutId: Int

    override fun onStart() {
        super.onStart()
        AnalyticsTrackerHolder.getInstance()?.trackEvent(Events.START_SCREEN)
    }

    override fun onStop() {
        AnalyticsTrackerHolder.getInstance()?.trackEvent(Events.STOP_SCREEN)
        super.onStop()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.ModalDialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.ModalDialog)
        setHasOptionsMenu(true)
        activity?.application?.let { inject(it) }
    }

    abstract fun inject(application: Application)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        processArguments(arguments)
        setupViews(savedInstanceState)
        subscribe()
    }

    protected open fun processArguments(bundle: Bundle?) {}

    protected open fun setupViews(savedInstanceState: Bundle?) {}

    protected open fun subscribe() {}

    override fun onDestroyView() {
        clearViews()
        super.onDestroyView()
    }

    protected open fun clearViews() {}
}