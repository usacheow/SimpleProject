package com.kapmayn.coreui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.kapmayn.core.base.Router
import javax.inject.Inject

abstract class MvvmFragment<ROUTER : Router> : SimpleFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var router: ROUTER

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
    }

    protected open fun subscribe() {}
}