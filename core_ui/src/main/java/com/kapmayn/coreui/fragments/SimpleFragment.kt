package com.kapmayn.coreui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kapmayn.di.BaseProvider
import com.kapmayn.di.DaggerApp

abstract class SimpleFragment : Fragment() {

    protected abstract val layoutId: Int

    protected var bottomDialog: BottomSheetDialog? = null
    protected var messageDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((activity?.application as DaggerApp).baseProvider)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(savedInstanceState)
    }

    override fun onStop() {
        super.onStop()
        bottomDialog?.cancel()
        messageDialog?.cancel()
    }

    protected abstract fun inject(applicationProvider: BaseProvider)

    protected open fun setupViews(savedInstanceState: Bundle?) {}

    protected fun getActivity(action: FragmentActivity.(FragmentActivity) -> Unit) {
        activity?.let { it.action(it) }
    }

    open fun onBackPressed(): Boolean {
        return false
    }
}
