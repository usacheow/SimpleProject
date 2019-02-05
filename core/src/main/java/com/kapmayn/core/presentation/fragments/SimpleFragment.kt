package com.kapmayn.core.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kapmayn.core.analytics.AnalyticsTrackerHolder
import com.kapmayn.diproviders.DiApp
import com.kapmayn.diproviders.DiProvider

abstract class SimpleFragment : Fragment() {

    protected abstract val layoutId: Int

    protected var bottomDialog: BottomSheetDialog? = null
    protected var messageDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((activity?.application as DiApp).diProvider)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { processArguments(it) }
        setupViews(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        AnalyticsTrackerHolder.getInstance().logEvent("onStart = ${this::class.java.simpleName}", null)
    }

    override fun onStop() {
        AnalyticsTrackerHolder.getInstance().logEvent("onStop = ${this::class.java.simpleName}", null)
        bottomDialog?.cancel()
        messageDialog?.cancel()
        super.onStop()
    }

    protected abstract fun inject(applicationProvider: DiProvider)

    protected open fun processArguments(bundle: Bundle) {}

    protected open fun setupViews(savedInstanceState: Bundle?) {}

    protected fun getActivity(action: FragmentActivity.(FragmentActivity) -> Unit) {
        activity?.let { it.action(it) }
    }

    open fun onBackPressed(): Boolean {
        return false
    }
}