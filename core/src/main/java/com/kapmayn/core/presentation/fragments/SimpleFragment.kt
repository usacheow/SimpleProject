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
import com.kapmayn.core.analytics.Events
import com.kapmayn.core.presentation.base.IContainer

abstract class SimpleFragment : Fragment() {

    protected abstract val layoutId: Int

    protected var bottomDialog: BottomSheetDialog? = null
    protected var messageDialog: AlertDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        processArguments(arguments)
        setupViews(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        AnalyticsTrackerHolder.getInstance().trackEvent("start ${this::class.java.simpleName}", Events.APP)
    }

    override fun onStop() {
        AnalyticsTrackerHolder.getInstance().trackEvent("stop ${this::class.java.simpleName}", Events.APP)
        bottomDialog?.cancel()
        messageDialog?.cancel()
        super.onStop()
    }

    protected open fun processArguments(bundle: Bundle?) {}

    protected open fun setupViews(savedInstanceState: Bundle?) {}

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

    open fun onBackPressed(): Boolean {
        return false
    }

    open fun getSharedViews() = emptyList<View>()
}